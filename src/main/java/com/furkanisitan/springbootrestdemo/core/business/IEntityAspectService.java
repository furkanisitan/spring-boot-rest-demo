package com.furkanisitan.springbootrestdemo.core.business;

import com.furkanisitan.springbootrestdemo.core.entities.IEntity;

public interface IEntityAspectService<T extends IEntity, ID> {

    boolean existById(ID id);

}
