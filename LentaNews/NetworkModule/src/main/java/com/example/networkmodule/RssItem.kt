import org.w3c.dom.Element
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.collections.ArrayList

class RssItem(val title: String, val description: String, val pubDate: Date, val link: String) {

    override fun toString(): String {

        val sdf = SimpleDateFormat("MM/dd - hh:mm:ss")

        return title + "  ( " + sdf.format(pubDate) + " )"
    }

    companion object {

        fun getRssItems(feedUrl: String): ArrayList<RssItem> {

            val rssItems = ArrayList<RssItem>()

//            val rssItemT = RssItem(
//                "MSUG news", "Best IT news.",
//                Date(), "http://msug.vn.ua/"
//            )
//
//            rssItems.add(rssItemT)

            try {

                val url = URL(feedUrl)
                val conn = url.openConnection() as HttpURLConnection

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    val `is` = conn.getInputStream()

                    val dbf = DocumentBuilderFactory
                        .newInstance()

                    val db = dbf.newDocumentBuilder()

                    val document = db.parse(`is`)
                    val element = document.getDocumentElement()

                    val nodeList = element.getElementsByTagName("item")

                    if (nodeList.getLength() > 0) {

                        for (i in 0 until nodeList.getLength()) {

                            val entry = nodeList.item(i) as Element

                            val tittleElement = entry
                                .getElementsByTagName("title")
                                .item(0) as Element

                            val descriptionElement = entry
                                .getElementsByTagName("description")
                                .item(0) as Element

                            val pubDateElement = entry
                                .getElementsByTagName("pubDate")
                                .item(0) as Element

                            val linkElement = entry
                                .getElementsByTagName("link")
                                .item(0) as Element

                            val Tittle = tittleElement.getFirstChild().getNodeValue()
                            val Description = descriptionElement.getFirstChild().getNodeValue()
                            val PubDate = Date(pubDateElement.getFirstChild().getNodeValue())
                            val Link = linkElement.getFirstChild().getNodeValue()

                            val rssItem = RssItem(
                                Tittle, Description,
                                PubDate, Link
                            )

                            rssItems.add(rssItem)
                        }
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return rssItems
        }
    }

}
