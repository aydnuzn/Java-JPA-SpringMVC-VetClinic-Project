# Java-JPA-SpringMVC-VetClinic-Project

* Veteriner projesi olan bu projede, veteriner kliniklerinin genel ihtiyaçlarını karşılamaya yönelik, hızlı ve pratik bir şekilde işlemlerin gerçekleştirilmesi amacıyla bu proje tasarlanmıştır. Bu proje Spring-Boot framework kullanılarak MVC tasarım desenine uyularak tasarlanmıştır. Tasarlanma aşamasında veri tabanı olarak MySql kullanılmıştır. Veri tabanını yönetmek için ORM araçlarından JPA kullanılmıştır. Ara yüz geliştirilirken HTML, CSS, BootStrap5 ve JavaScript kullanılmıştır.  Back-end kısmında Java, JavaScript dilleri kullanılmıştır. JavaScript tarafında JQuery kullanılmıştır.  (jQuery’de Ajax yöntemleri)

* In this project, which is a veterinary project, this project has been designed in order to meet the general needs of veterinary clinics and to carry out the procedures in a fast and practical way. This project was designed using the Spring-Boot framework, following the MVC design pattern. MySql was used as the database during the design phase. JPA, one of the ORM tools, was used to manage the database. While developing the interface, HTML, CSS, BootStrap5 and JavaScript were used. Java and JavaScript languages are used in the back-end part. JQuery is used on the JavaScript side. (Ajax methods in jQuery)

## Used Technologies
| Languages & Abilities |
|-----------------------|
|        Java           |
|     Spring-Boot       |
|   Spring Security     |
|      Spring-MVC       |
|    Spring-RestApi     |
|      Spring-JPA       |
|      JPA-Auditing     |
|   Spring-Validation   |
|        Regex          |
|        Log4j          |
|      Thymeleaf        |
|      JavaScript       |
|        jQuery         |
|         HTML          |
|         CSS           |
|       Bootstrap 5     |
|         MySql         |

### Thymeleaf

* Arayüz geliştirilirken Thymeleaf tema motoru kullanılmıştır. Thymeleaf’te master-page yöntemi kullanılarak hiyerarşik tarzda düzen oluşturulmuştur böylece hem kod tekrarından kurtarılarak clean code amaçlanmış hem de ara yüz tasarımının kontrolü kolaylaştırılmıştır.

* Thymeleaf theme engine was used while developing the interface. In Thymeleaf, a hierarchical layout was created by using the master page method, so that clean code is aimed by avoiding code repetition and the control of the interface design is facilitated.

### Spring Security

* Projemizde sayfalara erişim, yetkisiz işlem yapma gibi olumsuz durumları engellemek için güvenlik tarafında Spring Security kullanılmıştır. Kullanıcılara roller verilerek, belli sayfalara erişim hakkı tanımlanmış belli sayfalara da erişimi engellenmiştir. Dışarıdan gelebilecek saldırılara karşı programımız daha güvenli hale getirilmesi amaçlanmıştır.

* In our project, Spring Security is used on the security side to prevent negative situations such as accessing pages and making unauthorized transactions. By assigning roles to users, access to certain pages with the right to access certain pages is also blocked. It is aimed to make our program more secure against external attacks.

### Log4j

* Projemizde sorun olması durumunda sorunun daha kolay bulunabilmesi için günlüğe hata mesajları kayıt edilmektedir. Log4j uygulama deploy edildikten sonra arka planda uygulamanın konsol kısmında hata mesajlarını yazmayarak programın gereksiz yere işlem yapmasını önler.

* In case of a problem in our project, error messages are recorded in the log so that the problem can be found more easily. After the application is deployed, Log4j does not write error messages in the console part of the application in the background, preventing the program from taking unnecessary action.

### Validation

* Kullanıcıdan veri girişi olacağı vakit gelen bilgilerin kontrolü için doğrulama «validation» kullanılmıştır. Kullanıcı veri girişi yaparken HTML kısmında zorunlu kılınan alanlarda değişiklik yapabilir bu sorunun önüne geçebilmek için Spring Validation kullanılmıştır. Kullanıcının eksik veya yanlış bilgi girmesi durumunda Java tarafında kontrol edilmekte ve kullanıcının girmesi gereken formatta değişiklik yapması engellenmektedir.

* Validation is used to control the information received from the user when data is entered. Spring Validation has been used to prevent this problem. In case the user enters incomplete or incorrect information, it is controlled by Java and the user is prevented from making changes in the required format.


## Available Demo Accounts
| :closed_lock_with_key: Username | :old_key: Password |
|----------|----------|
| **``aydin@mail.com``**| **12345**|
| **``selen@mail.com``**| **1234**|
| **``mert@mail.com``**| **12345**|
| **``demo@mail.com``**| **12345**|

## Roles of Existing Available Demo Accounts
| :lock_with_ink_pen: Username | ROLE_ADMIN | ROLE_DOCTOR | ROLE_SECRETARY | ROLE_BEGINNER
|----------|----------|----------|----------|----------|
| **``aydin@mail.com``**| X | X | X | X 
| **``selen@mail.com``**| X | X | X | X 
| **``mert@mail.com``**| X | X | X | X 
| **``demo@mail.com``**|  |  |  | X 


## Application Images
<p>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/1.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/1.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/2.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/2.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/3.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/3.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/4.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/4.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/5.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/5.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/6.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/6.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/7.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/7.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/8.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/8.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/9.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/9.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/10.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/10.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/11.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/11.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/12.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/12.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/13.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/13.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/14.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/14.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/15.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/15.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/16.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/16.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/17.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/17.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/18.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/18.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/19.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/19.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/20.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/20.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/21.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/21.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/22.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/22.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/23.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/23.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/24.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/24.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/25.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/25.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/26.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/26.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/27.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/27.jpg" width="240" style="max-width:100%;"></a>

<a href="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/28.jpg" target="_blank">
<img src="https://github.com/aydnuzn/Java-JPA-SpringMVC-VetClinic-Project/blob/main/screenshoot/28.jpg" width="240" style="max-width:100%;"></a>
  
</p>
    
### Contact Me For More Information  

<a href="https://www.linkedin.com/in/aydın-uzun-a251b01a3" target="_blank">LinkedIn</a>
</br>
<a href="mailto:uzun.aydinn@gmail.com" target="_blank">Gmail</a>
