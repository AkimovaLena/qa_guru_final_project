# Дипломный проект для qa_guru (UI\API) [**Читай город**](https://www.x5.ru/)
<img width="30%" title="Читай город" src="media/logo/chotay_gorod.png">


## 	:world_map: Содержание:

- [Используемый стек](#computer-используемый-стек)
- [Описание автотестов](#pushpin-описание-тестов)
- [Запуск автотестов](#arrow_forward-запуск-автотестов)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Пример Allure-отчета](#-пример-allure-отчета)
- [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
- [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)
- [Видео примера запуска тестов в Selenoid](#видео-примера-запуска-тестов-в-selenoid)

##  :computer: Используемый стек
<p align="center">
<img width="6%" title="IntelliJ IDEA" src="media/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="media/logo/Java.svg">
<img width="6%" title="Selenide" src="media/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="media/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="media/logo/Allure_Report.svg">
<img width="6%" title="Allure TestOps" src="media/logo/AllureTestOps.svg">
<img width="6%" title="Gradle" src="media/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="media/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="media/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="media/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="media/logo/Telegram.svg">
</p>

Тесты в данном проекте написаны на языке <code>Java</code> с использованием фреймворка для тестирования [Selenide](https://selenide.org/), сборщик - <code>Gradle</code>. <code>JUnit 5</code> задействован в качестве фреймворка модульного тестирования.
При прогоне тестов для запуска браузеров используется [Selenoid](https://aerokube.com/selenoid/).
Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота.

## :pushpin: Описание тестов:
- [x] *java/tests/steps - папка с API тестами*
  - *CartTest - Сьют для тестов по Корзине*
  - *CatalogTest - Сьют для тестов по Каталогу*
- [x] *java/tests/web - папка с UI тестами*
  - *CartTest - Сьют для тестов по Главной странице*
  - *CartTest - Сьют для тестов по Корзине*
  - *CatalogTest - Сьют для тестов по Каталогу*



Содержание Allure-отчета:
* Шаги теста;
* Скриншот страницы на последнем шаге;
* Page Source (возможность открыть source страницы в новой вкладке и посмотреть причину падения теста);
* Логи браузерной консоли;
* Видео выполнения автотеста.

## :arrow_forward: Запуск автотестов

### Запуск тестов из терминала
Локальный запуск.
Из корневой директории проекта выполнить:


Для запуска API тестов

``` bash
gradle clean steps
```

Для запуска UI тестов

``` bash
gradle clean ui
```

Для запуска UI и API тестов по главной странице

``` bash
gradle clean main_page
```

Для запуска UI и API тестов по Корзине

``` bash
gradle clean cart
```
Для запуска UI и API тестов по Каталогу

``` bash
gradle clean catalog
```

Параметры, которыми можно управлять (указывать их необязательно):

```
-Dremote_browser
-Dstand
-Dbrowser
-Dversion
-Dbrowser_size
```

### Запуск тестов на удаленном браузере
```
clean
${TASK}
-Dremote_browser=${WDHOST}
-Dstand=${Stand}
-Dbrowser=${Browser}
-Dversion=${Version}
-Dbrowser_size=${BrowserSize}
```
###  Параметры сборки в Jenkins:
- TASK (набор тестов для запуска)
- stand (адрес основной страницы тестируемого сайта)
- remote_browser (адрес удаленного сервера, на котором будут выполняться тесты)
- browser_size (размер окна браузера, по умолчанию 1920x1080)
- version (версия браузера, по умолчанию 121)
- browser (браузер, по умолчанию chrome)


## <img src="media/logo/Jenkins.svg" title="Jenkins" width="4%"/> Сборка в Jenkins
Для запуска сборки необходимо перейти в раздел <code>Build with Parameters</code> и нажать кнопку <code>Build</code>.
<p align="center">
<img title="Jenkins Build" src="media/screens/JenkinsBuild.png">
</p>

## <img src="media/logo/Allure_Report.svg" title="Allure Report" width="4%"/> Пример Allure-отчета

<p align="center">
<img title="Allure Overview" src="media/screens/allureReport.png">
</p>


## <img src="media/logo/AllureTestOps.svg" title="Allure TestOps" width="4%"/> Интеграция с Allure TestOps

Выполнена интеграция сборки <code>Jenkins</code> с <code>Allure TestOps</code>.
Результат выполнения автотестов отображается в <code>Allure TestOps</code>
На Dashboard в <code>Allure TestOps</code> отображена статистика пройденных тестов.

<p align="center">
<img title="Allure TestOps DashBoard" src="media/screens/allureAutotestCloud.png">
</p>

### Результат выполнения автотеста

<p align="center">
<img title="Test Results in Alure TestOps" src="media/screens/allurResults.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Telegram" src="media/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки, бот созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.

<p align="center">
<img width="70%" title="Telegram Notifications" src="media/screens/notification.png">
</p>

## Видео примера запуска тестов в Selenoid

К каждому тесту в отчете прилагается видео прогона.
<p align="center">
  <img title="Selenoid Video" src="media/screens/video.gif">
</p>
