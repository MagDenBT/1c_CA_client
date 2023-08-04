package ch.magdenbt.a1ccaclient.model.scenarios.fakesource

import android.os.Build
import androidx.annotation.RequiresApi
import ch.magdenbt.a1ccaclient.model.NoCachedData
import ch.magdenbt.a1ccaclient.model.scenarios.DataSource
import ch.magdenbt.a1ccaclient.model.scenarios.entities.Scenario
import ch.magdenbt.a1ccaclient.model.scenarios.entities.ScenarioResult
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InMemoryDataSource : DataSource {

    override fun getCachedData(): Flow<Scenario> {
        throw NoCachedData()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun getActualData(): List<Scenario> = fakeData

}

@RequiresApi(Build.VERSION_CODES.R)
private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")


@RequiresApi(Build.VERSION_CODES.R)
val fakeData = listOf(
    Scenario(
        "f61c9156-bf1d-4c10-9baf-6a31680d4b01",
        LocalDateTime.parse("20.07.2023 19:07:47", formatter),
        ScenarioResult.Error,
        "ТЦ ГрандПарк Грозный Центральная 20А",
        "Не удалось выполнить бэкап - сервер недоступен",
    ),
    Scenario(
        "e8c6e0b7-1f69-4f4a-ba8c-b20451a2ec04",
        LocalDateTime.parse("21.07.2023 20:15:30", formatter),
        ScenarioResult.Success,
        "ТЦ Центральный Москва ул. Тверская 15",
        "Операция завершена успешно",
    ),
    Scenario(
        "c437f411-04c5-42af-b92f-c7d76ed57fb0",
        LocalDateTime.parse("22.07.2023 12:30:10", formatter),
        ScenarioResult.Started,
        "ТЦ Европейский Санкт-Петербург пр. Невский 100",
        "Запущен процесс резервного копирования",
    ),
    Scenario(
        "5d429662-0c25-4284-971c-c4e7393f4e6d",
        LocalDateTime.parse("23.07.2023 09:45:05", formatter),
        ScenarioResult.Error,
        "ТЦ Мега Казань пр. Победы 100",
        "Ошибка при обработке данных",
    ),
    Scenario(
        "dc2d37fc-227f-40d5-84a1-52fb4d0dd51e",
        LocalDateTime.parse("24.07.2023 16:20:55", formatter),
        ScenarioResult.Success,
        "ТЦ Гудвин Ростов-на-Дону ул. Большая Садовая 5",
        "Операция выполнена успешно",
    ),
    Scenario(
        "7d7b7c4d-0dcb-413d-a5b1-0b42e2d7ea8f",
        LocalDateTime.parse("25.07.2023 14:30:25", formatter),
        ScenarioResult.Started,
        "ТЦ СитиМолл Екатеринбург ул. Ленина 50",
        "Запущен процесс обновления данных",
    ),
    Scenario(
        "10ee951c-2e82-4ce2-9029-6853c4d7161b",
        LocalDateTime.parse("26.07.2023 18:50:15", formatter),
        ScenarioResult.Error,
        "ТЦ Империя Новосибирск ул. Красный проспект 10",
        "Ошибка при загрузке данных",
    ),
    Scenario(
        "31d75ed5-8105-4e9d-9cfd-eae3a7d1cbaf",
        LocalDateTime.parse("27.07.2023 10:35:40", formatter),
        ScenarioResult.Success,
        "ТЦ Аврора Краснодар ул. Северная 25",
        "Операция прошла успешно",
    ),
    Scenario(
        "83abbeab-dfca-4a4c-894b-cf3a00f71e8b",
        LocalDateTime.parse("28.07.2023 22:12:30", formatter),
        ScenarioResult.Started,
        "ТЦ Сокол Пермь ул. Ленина 75",
        "Запущен процесс синхронизации",
    ),
    Scenario(
        "f676d0e2-d267-4ce7-b51e-26bba26b2559",
        LocalDateTime.parse("29.07.2023 11:25:55", formatter),
        ScenarioResult.Error,
        "ТЦ Галерея Челябинск ул. Молодогвардейцев 30",
        "Проблемы с подключением к серверу",
    ),
    Scenario(
        "ab5e35be-763c-4ed9-af95-44f37f19c855",
        LocalDateTime.parse("30.07.2023 08:05:12", formatter),
        ScenarioResult.Success,
        "ТЦ Атриум Ульяновск пр. Ленина 40",
        "Успешно завершено",
    ),
    Scenario(
        "5eb6a881-7f9b-4b26-8702-0972dd30953c",
        LocalDateTime.parse("31.07.2023 16:30:27", formatter),
        ScenarioResult.Started,
        "ТЦ Кристалл Владивосток ул. Светланская 20",
        "Процесс запущен",
    ),
    Scenario(
        "9799e5b1-d929-4a9c-b048-24fb3d2f24f3",
        LocalDateTime.parse("01.08.2023 07:40:59", formatter),
        ScenarioResult.Error,
        "ТЦ Максимир Красноярск ул. Карла Маркса 5",
        "Ошибка сервера",
    ),
    Scenario(
        "11ff1119-2ef5-4627-80b9-29b54ed6ce5b",
        LocalDateTime.parse("02.08.2023 15:18:34", formatter),
        ScenarioResult.Success,
        "ТЦ ПаркХаус Иркутск ул. Кирова 15",
        "Операция выполнена успешно",
    ),
    Scenario(
        "2eae7c53-51f3-448b-8af4-1c0ea5cd669e",
        LocalDateTime.parse("03.08.2023 09:55:20", formatter),
        ScenarioResult.Started,
        "ТЦ Мега Кемерово пр. Ленина 100",
        "Запущен процесс обновления",
    ),
    Scenario(
        "b48a3ca0-2eeb-4ae5-991b-94e60102e9b3",
        LocalDateTime.parse("04.08.2023 13:25:08", formatter),
        ScenarioResult.Error,
        "ТЦ Московский Омск ул. Московская 10",
        "Проблемы с подключением",
    ),
    Scenario(
        "b48a3ca0-2eeb-4ae5-991b-94e60102e9b3",
        LocalDateTime.parse("05.08.2023 12:10:45", formatter),
        ScenarioResult.Success,
        "ТЦ Радуга Тюмень пр. Ленина 35",
        "Операция прошла успешно",
    ),
    Scenario(
        "b9e1f72f-91f6-400a-b48f-41ec4e43c1b1",
        LocalDateTime.parse("06.08.2023 20:47:33", formatter),
        ScenarioResult.Started,
        "ТЦ Золотой Ярославль ул. Советская 55",
        "Запущен процесс синхронизации",
    ),
    Scenario(
        "a79c522d-0a0a-4ac2-b70c-7e7422209d04",
        LocalDateTime.parse("07.08.2023 14:30:11", formatter),
        ScenarioResult.Error,
        "ТЦ Северный Астрахань ул. Красноармейская 5",
        "Ошибка в данных",
    ),
    Scenario(
        "8a3b1f5b-2364-4210-924a-3cc8c7118d0a",
        LocalDateTime.parse("08.08.2023 16:55:29", formatter),
        ScenarioResult.Success,
        "ТЦ Карусель Киров ул. Ленинградская 75",
        "Операция завершена успешно",
    ),
    Scenario(
        "98be3ff9-02e3-4095-97a1-28797cf0c051",
        LocalDateTime.parse("09.08.2023 09:22:50", formatter),
        ScenarioResult.Started,
        "ТЦ Галерея Волгоград ул. Пушкинская 30",
        "Запущен процесс резервного копирования",
    ),
    Scenario(
        "98be3ff9-02e3-4095-97a1-28797cf0c051",
        LocalDateTime.parse("10.08.2023 11:05:15", formatter),
        ScenarioResult.Error,
        "ТЦ Планета Чебоксары ул. Калинина 25",
        "Ошибка при обработке данных",
    ),
    Scenario(
        "ff49b159-0c1f-4d1b-9e8a-4503e8b6da52",
        LocalDateTime.parse("11.08.2023 19:30:08", formatter),
        ScenarioResult.Success,
        "ТЦ Арена Саратов ул. Волжская 10",
        "Операция прошла успешно",
    ),
    Scenario(
        "9d3ef93c-9ed3-401d-a7f6-2d4604a4bf2f",
        LocalDateTime.parse("12.08.2023 15:47:59", formatter),
        ScenarioResult.Started,
        "ТЦ Космос Уфа пр. Октября 80",
        "Запущен процесс обновления",
    ),
    Scenario(
        "e28b3b44-df94-46d3-bd8d-6038cc823c89",
        LocalDateTime.parse("13.08.2023 17:10:22", formatter),
        ScenarioResult.Error,
        "ТЦ Континент Екатеринбург ул. Малышева 15",
        "Ошибка сервера",
    ),
    Scenario(
        "892af754-3654-4c6b-9aa3-846cd5a9e02b",
        LocalDateTime.parse("14.08.2023 12:30:40", formatter),
        ScenarioResult.Success,
        "ТЦ Новомолл Самара ул. Степана Разина 5",
        "Операция завершена успешно",
    ),

)
