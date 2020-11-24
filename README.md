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
| POST	 | /api/countries | Add country | [JSON](#countryadd) | [BAD_REQUEST](#countrybadrequest), [CONFLICT](#countryconflict) |
| PUT	 | /api/countries/{code} | Update country by code | [JSON](#countryupdate) | [BAD_REQUEST](#countrybadrequest), [NOT_FOUND](#countrynotfound) |
| DELETE | /api/countries/{code} | Delete country by code | | [NOT_FOUND](#countrynotfound), [CONFLICT](#countryconflictforeignkey) |

### Cities

| Method | Url | Description | Sample Request Body | Sample JSON Exception Responses |
| ------ | --- | ----------- | ------------------- | ------------------------------- |
| GET	 | /api/cities | Get all cities | | |
| GET	 | /api/cities/{id} | Get city by id | | [NOT_FOUND](#citynotfound) |
| POST	 | /api/cities | Add city | [JSON](#cityaddorupdate) | [BAD_REQUEST](#citybadrequest), [CONFLICT](#cityconflict) |
| PUT	 | /api/cities/{id} | Update city by id | [JSON](#cityaddorupdate) | [BAD_REQUEST](#citybadrequest), [NOT_FOUND](#citynotfound) |
| DELETE | /api/cities/{id} | Delete city by id | | [NOT_FOUND](#citynotfound) |

### Country Languages

| Method | Url | Description | Sample Request Body | Sample JSON Exception Responses |
| ------ | --- | ----------- | ------------------- | ------------------------------- |
| GET	 | /api/country-languages | Get all country languages| | |
| GET	 | /api/country-languages/{countryCode} | Get all country languages by country code | | [NOT_FOUND](#countrynotfound) |
| GET	 | /api/country-languages/get-all-by-language/{language} | Get all country languages by language | | |
| GET	 | /api/country-languages/{countryCode}/{language} | Get country language by country code and language | | [NOT_FOUND](#countrylanguagenotfound) |
| POST	 | /api/country-languages | Add country language | [JSON](#countrylanguageadd) | [BAD_REQUEST](#countrylanguagebadrequest), [CONFLICT](#countrylanguageconflict) |
| PUT	 | /api/country-languages/{countryCode}/{language} | Update country language by country code and language | [JSON](#countrylanguageupdate) | [NOT_FOUND](#countrylanguagenotfound), [BAD_REQUEST](#countrylanguagebadrequest) |
| DELETE | /api/country-languages/{countryCode}/{language} | Delete country language by country code and language | | [NOT_FOUND](#countrylanguagenotfound) |

Test them using postman or any other rest client.

## Sample JSON Request Bodies

<ul id="countryadd">
  <li>Add country ->  POST /api/countries</li>
</ul>

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

<br>
<ul id="countryupdate">
  <li>Update country by code -> PUT /api/countries/{code}</li>
</ul>

```json
{
	"name": "Sample Country 2",
	"continent": "Europe",
	"region": "Eastern Europe",
	"population": 81250859,
	"lifeExpectancy": 83.8
}
```

<br>
<ul id="cityaddorupdate">
  <li>Add city -> POST /api/cities</li>
  <li>Update city by id -> PUT /api/cities/{id}</li>
</ul>

```json
{
	"name": "Çarşamba",
	"countryCode": "TUR",
	"district": "Samsun",
	"population": 140301
}
```

<br>
<ul id="countrylanguageadd">
  <li>Add country language -> POST /api/country-languages</li>
</ul>

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

<br>
<ul id="countrylanguageupdate">
  <li>Update country language by country code and language -> PUT /api/country-languages/{countryCode}/{language}</li>
</ul>

```json
{
	"isOfficial": "F",
	"percentage": 0.89
}
```

## Sample JSON Exception Responses

<ul id="countrynotfound">
  <li>Get country by code -> GET /api/countries/{code}</li>
  <li>Get cities of country -> GET /api/countries/{code}/cities</li>
  <li>Get country languages of country -> GET /api/countries/{code}/country-languages</li>
  <li>Update country by code -> PUT /api/countries/{code}</li>
  <li>Delete country by code -> DELETE /api/countries/{code}</li>
  <li> Get all country languages by country code -> GET /api/country-languages/{countryCode}</li>
</ul>

```json
{
    "status": "NOT_FOUND",
    "message": "Country not found for parameters {code='ABY'}",
    "timestamp": "14-11-2020 15:39:16"
}
```

<br>
<ul id="countrybadrequest">
  <li>Add country -> POST /api/countries</li>
  <li>Update country by code -> PUT /api/countries/{code}</li>
</ul>

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

<br>
<ul id="countryconflict">
  <li>Add country -> POST /api/countries</li>
</ul>

```json
{
    "status": "CONFLICT",
    "message": "A record with the same identifier value(code:'ABS') already exists in the database.",
    "timestamp": "14-11-2020 16:48:12"
}
```

<br>
<ul id="countryconflictforeignkey">
  <li>Delete country by code -> DELETE /api/countries/{code}</li>
</ul>

```json
{
    "status": "CONFLICT",
    "message": "The country cannot be deleted because there are CountryLanguage records that use this {code='abc'} parameter as a foreign key.",
    "timestamp": "14-11-2020 16:51:29"
}
```

<br>
<ul id="citynotfound">
  <li>Get city by id -> GET /api/cities/{id}</li>
  <li>Update city by id -> PUT /api/cities/{id}</li>
  <li>Delete city by id -> DELETE /api/cities/{id}</li>
</ul>

```json
{
    "status": "NOT_FOUND",
    "message": "Country not found for parameters {code='ABY'}",
    "timestamp": "14-11-2020 15:39:16"
}
```

<br>
<ul id="citybadrequest">
  <li>Add city -> POST /api/cities</li>
  <li>Update city by id -> PUT /api/cities/{id}</li>
</ul>

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

<br>
<ul id="cityconflict">
  <li>Add city -> POST /api/cities</li>
</ul>

```json
{
    "status": "CONFLICT",
    "message": "A record with the same identifier value(id:'4089') already exists in the database.",
    "timestamp": "14-11-2020 17:21:18"
}
```

<br>
<ul id="countrylanguagenotfound">
  <li>Get country language by country code and language -> GET /api/country-languages/{countryCode}/{language}</li>
  <li>Update country language by country code and language -> PUT /api/country-languages/{countryCode}/{language}</li>
  <li>Delete country language by country code and language -> DELETE /api/country-languages/{countryCode}/{language}</li>
</ul>

```json
{
    "status": "NOT_FOUND",
    "message": "CountryLanguage not found for parameters {countryLanguageId='CountryLanguageId(countryCode=TUR, language=Arab)'}",
    "timestamp": "14-11-2020 17:25:29"
}
```

<br>
<ul id="countrylanguagebadrequest">
  <li>Add country language -> POST /api/country-languages</li>
  <li>Update country language by country code and language -> PUT /api/country-languages/{countryCode}/{language}</li>
</ul>

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

<br>
<ul id="countrylanguageconflict">
  <li>Add country language -> POST /api/country-languages</li>
</ul>

```json
{
    "status": "CONFLICT",
    "message": "A record with the same identifier value(countryLanguageId:'CountryLanguageId(countryCode=TUR, language=Kurdish)') already exists in the database.",
    "timestamp": "14-11-2020 17:33:30"
}
```
