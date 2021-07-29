# AmarisPOC

## Uygulamanın çalıştırılması

Uygulamanın çalıştırılması için application.yaml dosyasını içerisindeki oracle database konfigürasyonunun yapılması gerekmektedir. Uygulama ilgili schema içerisine tabloları otomatik olarak create edecek şekilde configure edilmiştir.

## Rest Service'ler

Service'ler geliştirilirken, Spring Reactive Web kullanılmıştır.

## Rest Service Endpoint adresleri

### Swagger

Swagger URL       : http://localhost:8080/swagger-ui.html


### Application property listesini almak için : 

Bu serviste paging yapılmıştır. Aşağıdaki örnekte ilk 20 adet kayıt getirilmektedir. 

Service Adresi  : http://localhost:8080/api/v1/applications/properties?pageIndex=0&pageSize=20

Method          : GET


### Application property create etmek için : 

Service Adresi  : http://localhost:8080/api/v1/applications/properties 

Method          : POST


{
    "key": "asdasdasd",
    "value":"aaaaaa",
    "valueEncrypted": "Y", //value attribute'unun database'e encrypted veya raw şekilde kaydedilmesi için Y (Yes) veya N (No) değeri gönderilmelidir.
    "odmValue": "bbbbb",
    "odmValueEncrypted": "N", //odm value attribute'unun database'e encrypted veya raw şekilde kaydedilmesi için Y (Yes) veya N (No) değeri gönderilmelidir.
    "gpValue": "ccccc",
    "gpValueEncrypted": "Y",  //gp value attribute'unun database'e encrypted veya raw şekilde kaydedilmesi için Y (Yes) veya N (No) değeri gönderilmelidir.
    "description" : "test description"
}


### Application property update etmek için : 

Service Adresi  : http://localhost:8080/api/v1/applications/properties 

Method          : PUT

Örnek Json:

{
    "id": 1,
    "key": "asdasdasd",
    "value":"aaaaaa",
    "valueEncrypted": "Y", //value attribute'unun database'e encrypted veya raw şekilde kaydedilmesi için Y (Yes) veya N (No) değeri gönderilmelidir.
    "odmValue": "bbbbb",
    "odmValueEncrypted": "N", //odm value attribute'unun database'e encrypted veya raw şekilde kaydedilmesi için Y (Yes) veya N (No) değeri gönderilmelidir.
    "gpValue": "ccccc",
    "gpValueEncrypted": "Y",  //gp value attribute'unun database'e encrypted veya raw şekilde kaydedilmesi için Y (Yes) veya N (No) değeri gönderilmelidir.
    "description" : "test description"
}

## Şifreleme
   
   İlgili değerler database'e şifreli bir şekilde kaydedileceği zaman şifreleme için AES simetrki şifreleme yöntemi kullanılmıştır.

## Logging

Loglama için logback kullanılmıştır. 

## Service Request Logging

Uygulama içerisindeki Request interceptor. gelen requestin detaylarını database'e kaydeder.

## Unit Test

Application Property servisleri için Unit Test'ler oluşturulmuştur.

## Dockerize

Uygulama'nın dockerize edilebilmesi için Dockerfile base path altına eklenmiştir.
