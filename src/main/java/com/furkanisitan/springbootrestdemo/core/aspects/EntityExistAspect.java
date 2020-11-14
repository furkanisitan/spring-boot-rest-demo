package com.furkanisitan.springbootrestdemo.core.aspects;

import com.furkanisitan.springbootrestdemo.core.aspects.annotations.AspectEntityExist;
import com.furkanisitan.springbootrestdemo.core.aspects.helpers.GenericHelper;
import com.furkanisitan.springbootrestdemo.core.business.IEntityAspectService;
import com.furkanisitan.springbootrestdemo.core.entities.IEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.HashMap;

@Aspect
@Component
public class EntityExistAspect {

    private final HashMap<AspectEntityExist.DbOperation, DbOperation> dbOperationHashMap;
    private final BeanFactory beanFactory;

    public EntityExistAspect(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        dbOperationHashMap = new HashMap<>() {{
            put(AspectEntityExist.DbOperation.CREATE, new CreateOperation());
            put(AspectEntityExist.DbOperation.READ, new ReadOperation());
            put(AspectEntityExist.DbOperation.UPDATE, new UpdateOperation());
        }};
    }

    /**
     * Throws an exception depending on whether the entity exists or not and DbOperation enum value.
     * WARNING: Operation is done according to the first parameter given to the method.
     */
    @Before("@annotation(aspectEntityExist)")
    public void entityExist(JoinPoint joinPoint, AspectEntityExist aspectEntityExist) throws IllegalAccessException {

        var entityName = GenericHelper.getActualTypeArgumentClass(
                GenericHelper.getGenericInterfaceType(aspectEntityExist.service())).getSimpleName();

        var service = beanFactory.getBean(aspectEntityExist.service());

        dbOperationHashMap.get(aspectEntityExist.dbOperation()).handle(joinPoint, service, entityName);
    }

    private abstract static class DbOperation {

        @Getter
        @AllArgsConstructor
        private static class IdInfo {
            private String name;
            private Object value;
        }

        abstract void handle(JoinPoint joinPoint, IEntityAspectService service, String entityName) throws IllegalAccessException;

        /**
         * It assumes that the first parameter given in the method is id.
         * @return Id name and value
         */
        protected static IdInfo getIdInfoById(JoinPoint joinPoint) {

            var args = joinPoint.getArgs();
            if (args.length == 0) return null;

            return new IdInfo(((CodeSignature) joinPoint.getSignature()).getParameterNames()[0], args[0]);
        }

        /**
         * It assumes that the first parameter given in the method is entity.
         * Finds the id field of the entity object.
         * @return Id name and value
         */
        protected static IdInfo getIdInfoByEntity(JoinPoint joinPoint) throws IllegalAccessException {

            var args = joinPoint.getArgs();
            if (args.length == 0) return null;

            var entity = args[0];
            if (!(entity instanceof IEntity)) return null;

            var idField = getIdField(entity);
            if (idField == null) return null;

            return new IdInfo(idField.getName(), idField.get(entity));
        }

        /**
         * Returns the field marked with an @Id or @EmbeddedId.
         */
        private static Field getIdField(Object entity) {

            for (Field field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
                    field.setAccessible(true);
                    return field;
                }
            }
            return null;
        }
    }

    private static class CreateOperation extends DbOperation {

        @Override
        void handle(JoinPoint joinPoint, IEntityAspectService service, String entityName) throws IllegalAccessException {
            var idInfo = getIdInfoByEntity(joinPoint);
            if (service.existById(idInfo.value))
                throw new EntityExistsException(String.format("A record with the same identifier value(%s:'%s') already exists in the database.", idInfo.name, idInfo.value));
        }
    }

    private static class ReadOperation extends DbOperation {

        @Override
        void handle(JoinPoint joinPoint, IEntityAspectService service, String entityName) {
            var idInfo = getIdInfoById(joinPoint);
            if (!service.existById(idInfo.value))
                throw new EntityNotFoundException(String.format("%s not found for parameters {%s='%s'}", entityName, idInfo.name, idInfo.value));
        }
    }

    private static class UpdateOperation extends DbOperation {

        @Override
        void handle(JoinPoint joinPoint, IEntityAspectService service, String entityName) throws IllegalAccessException {
            var idInfo = getIdInfoByEntity(joinPoint);
            if (!service.existById(idInfo.value))
                throw new EntityNotFoundException(String.format("%s not found for parameters {%s='%s'}", entityName, idInfo.name, idInfo.value));
        }
    }
}

