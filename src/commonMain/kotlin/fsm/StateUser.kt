package fsm

/**
 * Every class who can use [State]s has to implement this interface. It's basically just a wrapper class for
 * a [StateManager]
 */
interface StateUser {
    val manager: StateManager

    fun setStartState(state: StateExecutor) {
        manager.setStartState(state)
    }

    fun doStateChange(state: StateExecutor) {
        manager.doStateChange(state)
    }

    fun updateCurrentState(dt: Double) {
        manager.updateCurrentState(dt)
    }

    fun getCurrentState(): StateExecutor {
        return manager.getCurrentState()
    }
}

/**
 * Creates a new [StateExecutor] and adds it to the [StateManager].
 * Optionally you can pass in lambdas for onBegin, onExecute and onEnd
 * Keep in mind to first create a [StateManager] with [useStates] to avoid errors
 */
inline fun <reified T: StateUser> T.declareState(noinline onBegin: () -> Unit = {}, noinline onExecute: (dt: Double) -> Unit = {}, noinline onEnd: () -> Unit = {}): StateExecutor {
    val currentManager = StateManager.stateMachines[T::class]
    if (currentManager != null) {
        return currentManager.createState(onBegin, onExecute, onEnd)
    }
    error("You have to declare a manager first! You can do this via useStates() ")
}

/**
 * Prepares everything for using states by creating a [StateManager]. You have to call this method first before
 * you can use [declareState]
 * Optionally you can add a callback which will be executed at creation
 */

inline fun <reified T: StateUser> T.useStates(callback: StateManager.() -> Unit = {}): StateManager {
    val manager = StateManager()
    StateManager.stateMachines[T::class] = manager
    manager.apply { callback() }
    return manager
}