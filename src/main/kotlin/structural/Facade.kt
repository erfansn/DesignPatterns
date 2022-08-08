package structural

import java.io.File
import java.nio.ByteBuffer

class VideoFile(val file: File) {
    fun save() = Unit
}

interface CompressionCodec
object OggCompressionCodec : CompressionCodec
object MPEG4CompressionCodec : CompressionCodec

class CodecFactory {
    companion object {
        fun extract(videoFile: VideoFile): CompressionCodec {
            return object : CompressionCodec { }
        }
    }
}
class BitrateReader {
    companion object {
        fun read(videoFile: VideoFile, compressionCodec: CompressionCodec) = ByteBuffer.wrap(byteArrayOf())!!
        fun convert(buffer: ByteBuffer, compressionCodec: CompressionCodec) = ByteBuffer.wrap(byteArrayOf())!!
    }
}
object AudioMixer {
    fun fix(buffer: ByteBuffer) = File("")
}

fun VideoFile.convert(format: String): VideoFile {
    val sourceCodec = CodecFactory.extract(this)

    val destinationCodec = if (format == "mp4") MPEG4CompressionCodec else OggCompressionCodec

    val buffer = BitrateReader.read(this, sourceCodec)
    val result = BitrateReader.convert(buffer, destinationCodec)
    val resultFile = AudioMixer.fix(result)

    return VideoFile(resultFile)
}

/**
 * Pros:
 * - You can isolate your code from the complexity of a subsystem.
 *
 * Cons:
 * - A facade can become a god object coupled to all classes of an app.
 */
fun main() {
    var videoFile = VideoFile(File(""))
    videoFile = videoFile.convert("mp4")
    videoFile.save()
}
