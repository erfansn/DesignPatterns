package structural

import kotlin.random.Random

typealias Id = Int

interface ThirdPartyYouTubeLib {
    val listVideos: List<Any>
    fun getVideoInfo(id: Id): Any
    fun downloadVideo(id: Id)
}

class ThirdPartyYouTubeClass : ThirdPartyYouTubeLib {
    override val listVideos: List<Any>
        get() = TODO("Not yet implemented")

    override fun getVideoInfo(id: Id) {
        TODO("Not yet implemented")
    }

    override fun downloadVideo(id: Id) {
        TODO("Not yet implemented")
    }
}

class CachedThirdPartyYouTubeClass(private val thirdPartyYoutubeLib: ThirdPartyYouTubeLib) : ThirdPartyYouTubeLib {

    private val videosInfo = mutableMapOf<Id, Any>()
    private val downloadedVideos = mutableMapOf<Id, Any>()
    private val requestRefresh: Boolean = Random.nextBoolean()

    override val listVideos: List<Any> by lazy {
        thirdPartyYoutubeLib.listVideos
    }

    override fun getVideoInfo(id: Id) {
        videosInfo[id].takeUnless {
            requestRefresh
        } ?: thirdPartyYoutubeLib.getVideoInfo(id).also {
            videosInfo[id] = it
        }
    }

    override fun downloadVideo(id: Id) {
        downloadedVideos[id].takeUnless {
            requestRefresh
        } ?: thirdPartyYoutubeLib.downloadVideo(id)
    }
}

class YouTubeManager(private val service: ThirdPartyYouTubeLib) {

    private lateinit var info: Any
    private lateinit var list: List<Any>

    fun renderVideoPage(id: Id) {
        info = service.getVideoInfo(id)
    }

    fun renderListPanel() {
        list = service.listVideos
    }
}

/**
 * Pros:
 * - You can control the service object without clients knowing about it.
 * - You can manage the lifecycle of the service object when clients don’t care about it.
 * - The proxy works even if the service object isn’t ready or is not available.
 * - Open/Closed Principle. You can introduce new proxies without changing the service or clients.
 *
 * Cons:
 * - The code may become more complicated since you need to introduce a lot of new classes.
 * - The response from the service might get delayed.
 *
 * [Type of proxy](https://refactoring.guru/design-patterns/proxy)
 */
fun main() {
    val thirdPartyYouTubeClass = ThirdPartyYouTubeClass()
    val cachedThirdPartyYouTubeClass = CachedThirdPartyYouTubeClass(thirdPartyYouTubeClass)
    val youTubeManager = YouTubeManager(cachedThirdPartyYouTubeClass)

    youTubeManager.renderVideoPage(12)
}
