package behavioral

sealed class State(protected val player: AudioPlayer) {
    abstract fun onClickLock()
    abstract fun onClickPlay()
    abstract fun onClickNext()
    abstract fun onClickPrevious()
}

class LockedState(audioPlayer: AudioPlayer) : State(audioPlayer) {
    override fun onClickLock() {
        player.currentState = ReadyState(player)
        println("Device unlocked")
    }

    override fun onClickPlay() = Unit

    override fun onClickNext() = Unit

    override fun onClickPrevious() = Unit
}

class ReadyState(audioPlayer: AudioPlayer) : State(audioPlayer) {
    override fun onClickLock() {
        player.currentState = LockedState(player)
        println("Device locked")
    }

    override fun onClickPlay() {
        player.startPlayback()
        player.currentState = PlayingState(player)
        println("Start playing media")
    }

    override fun onClickNext() {
        player.nextSong()
        println("Next song")
    }

    override fun onClickPrevious() {
        player.previousSong()
        println("Previous song")
    }
}

class PlayingState(audioPlayer: AudioPlayer) : State(audioPlayer) {
    override fun onClickLock() {
        player.currentState = LockedState(player)
        println("Device locked")
    }

    override fun onClickPlay() {
        player.stopPlayback()
        player.currentState = ReadyState(player)
        println("Stop playing media")
    }

    override fun onClickNext() {
        player.fastForward(5)
        println("Fast forward")
    }

    override fun onClickPrevious() {
        player.rewind(5)
        println("Rewind")
    }
}

class AudioPlayer {

    internal var currentState: State = ReadyState(this)

    fun clickLock() = currentState.onClickLock()
    fun clickPlay() = currentState.onClickPlay()
    fun clickNext() = currentState.onClickNext()
    fun clickPrevious() = currentState.onClickPrevious()

    // A state may call some service method s on the context.
    fun startPlayback() = Unit
    fun stopPlayback() = Unit
    fun nextSong() = Unit
    fun previousSong() = Unit
    fun fastForward(time: Long) = Unit
    fun rewind(time: Long) = Unit
}

/**
 * Pros:
 * - Single Responsibility Principle. Organize the code related to particular states into separate classes.
 * - Open/Closed Principle. Introduce new states without changing existing state classes or the context.
 * - Simplify the code of the context by eliminating bulky state machine conditionals.
 *
 * Cons:
 * - Applying the pattern can be overkill if a state machine has only a few states or rarely changes.
 */
fun main() {
    val audioPlayer = AudioPlayer()
    audioPlayer.clickPlay()
    audioPlayer.clickLock()
    audioPlayer.clickLock()
    audioPlayer.clickPlay()
    audioPlayer.clickNext()
}
