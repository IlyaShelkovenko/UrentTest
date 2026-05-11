# UrentTest

Тестовое Kotlin Multiplatform приложение для поиска городов и просмотра базовой информации о выбранном городе.

Проект содержит список городов, поле поиска, экран информации о городе и собственная небольшая дизайн-система с цветовыми, размерными и типографическими токенами.

## Возможности

- Загрузка списка городов с dev API.
- Поиск городов через query-параметр.
- Пагинация списка через `page` и `limit` с подгрузкой за 5 элементов до конца.
- Переход с экрана списка на экран информации о городе.
- Отображение города, страны и населения с форматированием вида `12 655 000 чел`.
- Открытие браузера с поисковым запросом по названию выбранного города.
- Общие Compose UI-компоненты для Android и iOS.

## Технологии

- **Kotlin Multiplatform** — общий код для Android и iOS.
- **Compose Multiplatform** — общий UI-слой.
- **Material 3** — базовая тема и UI primitives.
- **Orbit MVI** — presentation state management: `State`, `Action`, `SideEffect`, `ViewModel`.
- **Koin** — dependency injection и ViewModel injection.
- **Ktor Client** — сетевой слой.
- **kotlinx.serialization** — JSON-десериализация DTO.
- **Navigation Compose** — type-safe навигация между экранами.
- **OkHttp** — Ktor engine для Android.
- **Darwin** — Ktor engine для iOS.

## Архитектура

Проект разделён по feature/core/design слоям.

```text
composeApp/src/commonMain/kotlin/org/example/urent_test
├── feature
│   ├── cities_list
│   │   ├── data
│   │   ├── domain
│   │   ├── di
│   │   └── presentation
│   └── city_information
│       ├── di
│       └── presentation
├── core
│   ├── navigation
│   └── network
├── design
│   ├── components
│   ├── modifier
│   ├── theme
│   └── tokens
└── di
```

### Presentation

Экраны списка городов и информации о городе построены на Orbit MVI:

- `CitiesListState` — состояние экрана.
- `CitiesListAction` — пользовательские действия.
- `CitiesListSideEffect` — одноразовые эффекты, например навигация.
- `CitiesListViewModel` — Orbit `ContainerHost`, загрузка данных и обработка actions.
- `CitiesListRoot` — связывает ViewModel, state и side effects.
- `CitiesListScreen` — чистый composable, получает только state и action callback.
- `CityInformationViewModel` — обрабатывает действия экрана информации и отправляет side effect для открытия браузера.

Пагинация списка отделена от item rendering: `LazyListState` наблюдается через `snapshotFlow`, а `CitiesListAction.OnLoadNextPage` отправляется только при достижении зоны предзагрузки. ViewModel дополнительно защищает загрузку флагами `hasMore`, `isLoading` и `isLoadingNextPage`.

### Domain/Data

`CitiesRepository` скрывает источник данных от presentation-слоя. Реализация `CitiesRepositoryImpl` получает DTO через `UrentDevApi` и маппит их в domain-модели.

Дополнительно в data-слое есть `CountryNameMapper`, который преобразует ISO-коды стран в русские названия для отображения в UI.

### Network

Сетевой слой находится в `core/network`:

- `HttpClientFactory` создаёт общий Ktor `HttpClient`.
- `PlatformEngine` выбирает engine под платформу.
- `UrentDevApi` выполняет запрос `GET cities` с параметрами `query`, `page` и `limit`.
- DTO лежат в `core/network/dto`.

Base URL для Android задаётся в `composeApp/build.gradle.kts`:

```kotlin
buildConfigField(
    "String",
    "BASE_URL",
    "\"http://dev-dep.tools.urent.tech:8080/api/\""
)
```

Для Android включены `INTERNET` permission и `usesCleartextTraffic`, так как API использует HTTP.

## Дизайн-система

Дизайн-система находится в `design` и содержит:

- `ColorTokens` — цвета из Figma, включая `Text/Primary`, `Button/Default`, `Button/Pressed`, `Input/Background`.
- `TypographyTokens` — текстовые стили, включая `Middle/Upper Text`.
- `DimensionTokens` — размеры компонентов, радиусы, padding, ширины preview.
- `UrentTestTheme` — общая Compose-тема.
- UI-компоненты: search field, primary button, top bar, list item, city info field.
- Preview для компонентов и состояний.

## Навигация

Навигация реализована через type-safe routes:

- `Route.CitiesList`
- `Route.CityInformation`

При выборе города данные передаются на экран информации через route arguments.

На экране информации кнопка поиска отправляет action во ViewModel. ViewModel формирует URL поискового запроса по названию города и отдаёт его наружу через `CityInformationSideEffect.OpenBrowser`, а UI открывает ссылку через `LocalUriHandler`.

## Запуск Android

Из корня проекта:

```shell
.\gradlew.bat :composeApp:assembleDebug
```

Или через Android Studio run configuration для `composeApp`.

## Проверка сборки

Быстрая проверка Kotlin/Android части:

```shell
.\gradlew.bat :composeApp:compileDebugKotlinAndroid
```

## Обработка ошибок
- Если список городов не загружается, приложение показывает ошибку и кнопку для повтора загрузки.
