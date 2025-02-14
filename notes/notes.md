## Какво е слой?

Абстрактно разделение на проекта ни, което ни помага да мислим за него. 
Свързано със задачата, която всяка част от кода изпълнява. 

Например:   
- Един слой може да това в папката, в която се намират файлове свързани с дадена функционалност.   
  - В C++: може да отговарят на `namespace`   

## Какво е архитектура?

Абстрактна структура на кода:  
- Слоеве;   
- Как модулите са свързани помежду си.   

`модул` - група класове, свързани с дадена функционалност.   

*/високо ниво/*  
*/папките/*  

## Какво е софтуерен дизайн?  

Конкретното изпълнение на архитектурата. Как са имплементирани самите модули.  

*/ниско ниво/*  
*/това вътре в папките/*

## Coupling

Степента на свързаност между отделните модули.  

- Характеристика както на дизайна, така и на архитектурата. 
- Типове:
  - low/lose 
  - high 

*/Колко други модула се чупят, когато променим даден модул?/*  
**Външна свързаност**

## Cohesion

Степента, до която елементите в даден модул са смислово свързани помежду си.  
**Вътрешна свързаност**

Пример: 
- лоша кохезия в една функция от 5 реда -> четвъртият ред прави нещо отделно си.
- кохезия на ниво функция, клас, неймспейс, ...

##

*Понятията `Coupling` и `Cohesion` вървят ръка за ръка. Те са правопропорционални помежду си -> едното влече другото.*

*Те са метрика за това колко са добри или лоши архитектурата и дизайнът.*

*От тях зависи дали наистина ще имаме преизползваем код.* 


## SOLID Principles

> :robot: **Disclaimer: This section was AI-generated.**

Принципите на SOLID са пет основни принципа за проектиране на обектно-ориентиран софтуер, които помагат за създаването на по-разбираем, гъвкав и поддържан код.

1. **Single Responsibility Principle (SRP)**  
    Всеки клас трябва да има една единствена отговорност, т.е. да изпълнява една задача.

2. **Open/Closed Principle (OCP)**  
    Класовете трябва да са отворени за разширение, но затворени за модификация.

3. **Liskov Substitution Principle (LSP)**  
    Обектите от подтиповете трябва да могат да заместят обектите от базовите типове без да нарушават функционалността на програмата.

4. **Interface Segregation Principle (ISP)**  
    Клиентите не трябва да бъдат принуждавани да зависят от интерфейси, които не използват.

5. **Dependency Inversion Principle (DIP)**  
    Високо ниво модули не трябва да зависят от ниско ниво модули. И двата типа модули трябва да зависят от абстракции.

##
*SOLID принципите са добри практики, които подобряват метриките `coupling` и `кохезия`, когато се спазват. Те са указание как да постигнем добър дизайн -> добри `coupling` и `кохезия`.*  
*Design Patterns са схеми с цел постигане на горните.*  


# Изпит 

- Към края на сесията защита на проекта. 
- да се билдва 
- дизайн патърни :Д

## Оценяване 
на база 4-те домашни досега и двете предстоящо + защита.

***Ако сме без нищо може да ни дадат въпрос за 3 или пазарлък !***
### Домашни:
1. коли, обяви, хора, цени ...
   - Да има профили: аз правя обява, после като се логне сашо да я вижда 
2. филтри + сложни филтри
3. нотификации
4. query парсър - **не е важен за финалния проект**
Предстои:
5. конзолен интерфейс 
   - да не е с иф елс или суич - подходящи патърни, подходи
   - да си говори с нас:
     - какво ще добавиш
     - от коя година
     - ...
     - команда language switch за ui <- съвет - ще ни помогне за изгр на добър дизайн **бонус**
       - логиката отдолу да е отделена от езика, за да няма нужда от копи пейст
6. графиката с цените през годините
   - когато обява изтече, какво да я правим? - какво да запомним


- файл с примерна интеракция + демо на живо
