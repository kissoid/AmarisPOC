# AmarisPOC

## Uygulamanın çalıştırılması

Kurulu olduğu bilgisayardaki MongoDB'ye, default portu olan 27017 olan default portu üzerinden bağlanmaya çalışır. Bağlantı için ihtiyaç duyduğu database, kullanıcı adı ve şifre aşağıda  belirtilmiştir.

database: amaris,
username: amaris,
password: amaris

## Service endpoint adresleri

### Menu listesini almak için : 

Service Adresi  : http://localhost:8080/api/v1/menus/getMenuList 

Method          : GET

### Ürün lisesini almak için : 

Bu serviste paging yapılmıştır. Aşağıdaki örnekte ilk 20 adet kayıt getirilmektedir. Örnek oalrak uygulama açılışında dataabse'e 1000 adet kayıt atılacaktır. Tüm kayıtları bir defada görmek için pageSize değerini 100 veya üzeri bir değer olarak değiştirmeniz yeterlidir.

Service Adresi  : http://localhost:8080/api/v1/products/getProductList?pageIndex=0&pageSize=20

Method          : GET


### Ürün kısa numarasılarını update etmek için : 

BU servis request body'sinde Json listesi alır.

Service Adresi  : http://localhost:8080/api/v1/products/updateProductInfo

Method          : PUT

Örnek Json:

[
   {
      "id":1,
      "mobileNumber":"0532 555 92614",
      "username":"Test User : 1",
      "line":"Hat",
      "lineType":"Hat Tipi",
      "paymentType":"Ödeme Tipi",
      "shortNumber":48938
   },
   {
      "id":2,
      "mobileNumber":"0532 555 74308",
      "username":"Test User : 2",
      "line":"Hat",
      "lineType":"Hat Tipi",
      "paymentType":"Ödeme Tipi",
      "shortNumber":96610
   },
   {
      "id":3,
      "mobileNumber":"0532 555 16671",
      "username":"Test User : 3",
      "line":"Hat",
      "lineType":"Hat Tipi",
      "paymentType":"Ödeme Tipi",
      "shortNumber":90652
   },
   {
      "id":4,
      "mobileNumber":"0532 555 27017",
      "username":"Test User : 4",
      "line":"Hat",
      "lineType":"Hat Tipi",
      "paymentType":"Ödeme Tipi",
      "shortNumber":54725
   },
   {
      "id":5,
      "mobileNumber":"0532 555 70383",
      "username":"Test User : 5",
      "line":"Hat",
      "lineType":"Hat Tipi",
      "paymentType":"Ödeme Tipi",
      "shortNumber":58453
   }
]
