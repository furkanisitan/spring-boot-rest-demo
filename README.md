# Spring Boot, Rest API, MySQL, Spring AOP

Build Restful CRUD API using Spring Boot, Mysql, JPA, Hibernate and Spring AOP.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/furkanisitan/spring-boot-rest-demo.git
```

**2. Create Mysql database**

create database <a href="https://dev.mysql.com/doc/world-setup/en/world-setup-installation.html">world</a>

- run `src/main/resources/world.sql`

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Explore Rest APIs

The app defines following CRUD APIs.

### Countries

| Method | Url | Description | Sample Request Body | Sample JSON Exception Responses |
| ------ | --- | ----------- | ------------------- | ------------------------------- |
| GET	 | /api/countries | Get all countries | | |
| GET	 | /api/countries/{code} | Get country by code | | [NOT_FOUND](#countrynotfound) |
| GET	 | /api/countries/{code}/cities | Get cities of country | | [NOT_FOUND](#countrynotfound) |
| GET	 | /api/countries/{code}/country-languages | Get country languages of country | | [NOT_FOUND](#countrynotfound) |
| POST	 | /api/countries/add | Add country | [JSON](#countryadd) | [BAD_REQUEST](#countrybadrequest), [CONFLICT](#countryconflict) |
| POST	 | /api/countries/update | Update country | [JSON](#countryupdate) | [BAD_REQUEST](#countrybadrequest), [NOT_FOUND](#countrynotfound) |
| POST	 | /api/countries/delete-by-code?code={code} | Delete country by code | | [NOT_FOUND](#countrynotfound), [CONFLICT](#countryconflictforeignkey) |

### Cities

| Method | Url | Description | Sample Request Body | Sample JSON Exception Responses |
| ------ | --- | ----------- | ------------------- | ------------------------------- |
| GET	 | /api/cities | Get all cities | | |
| GET	 | /api/cities/{id} | Get city by id | | [NOT_FOUND](#citynotfound) |
| POST	 | /api/cities/add | Add city | [JSON](#cityadd) | [BAD_REQUEST](#citybadrequest), [CONFLICT](#cityconflict) |
| POST	 | /api/cities/update | Update city | [JSON](#cityupdate) | [BAD_REQUEST](#citybadrequest), [NOT_FOUND](#citynotfound) |
| POST	 | /api/cities/delete-by-id?id={id} | Delete city by id | | [NOT_FOUND](#citynotfound) |

### Country Languages

| Method | Url | Description | Sample Request Body | Sample JSON Exception Responses |
| ------ | --- | ----------- | ------------------- | ------------------------------- |
| GET	 | /api/country-languages | Get all country languages| | |
| GET	 | /api/country-languages/{countryCode} | Get all country languages by country code | | [NOT_FOUND](#countrynotfound) |
| GET	 | /api/country-languages/get-all-by-language/{language} | Get all country languages by language | | |
| GET	 | /api/country-languages/{countryCode}/{language} | Get country language by country code and language | | [NOT_FOUND](#countrylanguagenotfound) |
| POST	 | /api/country-languages/add | Add country language | [JSON](#countrylanguageadd) | [BAD_REQUEST](#countrylanguagebadrequest), [CONFLICT](#countrylanguageconflict) |
| POST	 | /api/country-languages/update | Update country language | [JSON](#countrylanguageupdate) | [NOT_FOUND](#countrylanguagenotfound), [BAD_REQUEST](#countrylanguagebadrequest) |
| POST	 | /api/country-languages/delete-by-country-language-id | Delete country language by countryLanguageId| [JSON](#countrylanguagedelete) | [NOT_FOUND](#countrylanguagenotfound) |

Test them using postman or any other rest client.

## Sample JSON Request Bodies

##### <a id="countryadd">Add country -> /api/countries/add</a>
```json
{
	"code": "ABC",
	"name": "Sample Country",
	"continent": "Europe",
	"region": "Eastern Europe",
	"population": 81250859,
	"lifeExpectancy": 77.9
}
```

##### <a id="countryupdate">Update country -> /api/countries/update</a>
```json
{
	"code": "ABC",
	"name": "Sample Country 2",
	"continent": "Europe",
	"region": "Eastern Europe",
	"population": 81250859,
	"lifeExpectancy": 83.8
}
```

##### <a id="cityadd">Add city -> /api/cities/add</a>
```json
{
	"name": "Çarşamba",
	"countryCode": "TUR",
	"district": "Samsun",
	"population": 140301
}
```

##### <a id="cityupdate">Update city -> /api/cities/update</a>
```json
{	
	"id": 4090, 
	"name": "Çarşamba",
	"countryCode": "TUR",
	"district": "Samsun",
	"population": 145897
}
```

##### <a id="countrylanguageadd">Add country language -> /api/country-languages/add</a>
```json
{
	"countryLanguageId": {
	    "countryCode": "TUR",
	    "language": "Sample Language"
	},
	"isOfficial": "F",
	"percentage": 0.14
}
```

##### <a id="countrylanguageupdate">Update country language -> /api/country-languages/update</a>
```json
{
	"countryLanguageId": {
	    "countryCode": "TUR",
	    "language": "Sample Language"
	},
	"isOfficial": "F",
	"percentage": 0.89
}
```

##### <a id="countrylanguagedelete">Delete country language by countryLanguageId -> /api/country-languages/delete-by-country-language-id</a>
```json
{
    "countryCode": "TUR",
    "language": "Sample Language"
}
```

## Sample JSON Exception Responses

##### <a id="countrynotfound"> Get country by code -> /api/countries/{code} <br> Get cities of country -> /api/countries/{code}/cities <br> Get country languages of country -> /api/countries/{code}/country-languages <br> Update country -> /api/countries/update <br> Delete country by code -> /api/countries/delete-by-code?code={code} <br> Get all country languages by country code -> /api/country-languages/{countryCode}</a>
```json
{
    "status": "NOT_FOUND",
    "message": "Country not found for parameters {code='ABY'}",
    "timestamp": "14-11-2020 15:39:16"
}
```

##### <a id="countrybadrequest"> Add country -> /api/countries/add <br> Update country -> /api/countries/update
```json
{
    "status": "BAD_REQUEST",
    "message": "Validation error",
    "timestamp": "14-11-2020 16:43:41",
    "subErrors": [
        {
            "object": "Country",
            "field": "continent",
            "rejectedValue": "Europed",
            "message": "Continent not one of the values accepted: [Asia, Africa, Antarctica, Europe, North America, Oceania, South America]"
        }
    ]
}
```

##### <a id="countryconflict"> Add country -> /api/countries/add
```json
{
    "status": "CONFLICT",
    "message": "A record with the same identifier value(code:'ABS') already exists in the database.",
    "timestamp": "14-11-2020 16:48:12"
}
```
	
##### <a id="countryconflictforeignkey"> Delete country by code -> /api/countries/delete-by-code?code={code}
```json
{
    "status": "CONFLICT",
    "message": "The country cannot be deleted because there are CountryLanguage records that use this {code='abc'} parameter as a foreign key.",
    "timestamp": "14-11-2020 16:51:29"
}
```

##### <a id="citynotfound"> Get city by id -> /api/cities/{id} <br> Update city -> /api/cities/update <br> Delete city by id -> /api/cities/delete-by-id?id={id}</a>
```json
{
    "status": "NOT_FOUND",
    "message": "Country not found for parameters {code='ABY'}",
    "timestamp": "14-11-2020 15:39:16"
}
```

##### <a id="citybadrequest"> Add city -> /api/cities/add <br> Update city -> /api/cities/update
```json
{
    "status": "BAD_REQUEST",
    "message": "Validation error",
    "timestamp": "14-11-2020 17:17:55",
    "subErrors": [
        {
            "object": "City",
            "field": "countryCode",
            "rejectedValue": "TR",
            "message": "CountryCode does not exist."
        }
    ]
}
```

##### <a id="cityconflict"> Add city -> /api/cities/add
```json
{
    "status": "CONFLICT",
    "message": "A record with the same identifier value(id:'4089') already exists in the database.",
    "timestamp": "14-11-2020 17:21:18"
}
```

##### <a id="countrylanguagenotfound"> Get country language by country code and language -> /api/country-languages/{countryCode}/{language} <br> Update country language -> /api/country-languages/update <br> Delete country language by countryLanguageId -> /api/country-languages/delete-by-country-language-id</a>
```json
{
    "status": "NOT_FOUND",
    "message": "CountryLanguage not found for parameters {countryLanguageId='CountryLanguageId(countryCode=TUR, language=Arab)'}",
    "timestamp": "14-11-2020 17:25:29"
}
```

##### <a id="countrylanguagebadrequest"> Add country language -> /api/country-languages/add <br> Update country language -> /api/country-languages/update</a>
```json
{
    "status": "BAD_REQUEST",
    "message": "Validation error",
    "timestamp": "14-11-2020 17:30:13",
    "subErrors": [
        {
            "object": "CountryLanguage",
            "field": "isOfficial",
            "rejectedValue": "False",
            "message": "IsOfficial not one of the values accepted: [T, F]"
        }
    ]
}
```
	
##### <a id="countrylanguageconflict"> Add country language -> /api/country-languages/add</a>
```json
{
    "status": "CONFLICT",
    "message": "A record with the same identifier value(countryLanguageId:'CountryLanguageId(countryCode=TUR, language=Kurdish)') already exists in the database.",
    "timestamp": "14-11-2020 17:33:30"
}
```
