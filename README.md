# LentaNews
Задание: Реализовать приложение для отображения и просмотра последних новостей с сайта https://lenta.ru. (загружать через RSS https://lenta.ru/info/posts/export/)
В общем списке отображать новости разбитые по категориям (top7, last24, all), в одной категории может быть только 4 новости, перед началом каждой категории отображать ее заголовок и кнопку "View", по нажатию на которую откроется новая страница с полным списком новостей из выбранной категории. 
Новость из категории top7 должна отображаться в общем списке как 1 новость в 1 строке, все остальные новости отображаются как 2 новости в 1 строке.
Что делать по нажатию на новость автор решает сам.

Технические требования: 
Для сетевой части создать отдельный модуль, который легко можно использовать в другом приложении.
Для загрузки картинок нельзя использовать сторонние библиотеки.

Экран 1:
—————
Top7 Category [View]
Item1
Item2
Item3
Item4
Last24 Category [View]
Item1 Item2
Item3 Item4
All [View]
Item1 Item2
Item3 Item4

Экран 2:
<Category name>
—————
Item1
Item2
Item3
Item4
Item5
Лента.Ру
lenta.ru
