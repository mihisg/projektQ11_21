package Scenes

//import fsm.Entity
import physic.Listener
import physic.*
import actor.Enemy
import actor.Player
import actor.actors.Bullet
import com.soywiz.klock.DateTime
import com.soywiz.korev.Key
import com.soywiz.korge.input.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.Point
import eventBus.*
import fsm.*
import org.jbox2d.common.Vec2

class TestScene : Scene() {
    override suspend fun Container.sceneInit() {

        //val bus = EventBus(this@TestScene)

        /*val testPlayer = Player.build("Characters/Test.xml", this@TestScene).apply { xy(100, 100); scale = 0.1 }
        val testEnemy = Enemy.build("Characters/Test.xml", this@TestScene).apply { xy(300, 300); scale = 0.1 }
        val testPlayer2 = Player.build("Characters/Test.xml", this@TestScene).apply { xy(500, 500); scale = 0.1 }
        val testBullet = Bullet.build("Characters/Test.xml", this@TestScene).apply { xy(700, 700); scale = 0.1 }
*/


        val s1 = SolidRect(100, 100, Colors.RED).xy(50, 50).apply { name = "Rot" }
        val s2 = SolidRect(200, 800, Colors.BLUE).xy(400, 400).apply { name = "Blau" }
        val s3 = SolidRect(50, 200, Colors.GREEN).xy(300, 500).apply { name = "Grün" }
        val s4 = SolidRect(75, 120, Colors.YELLOW).xy(100, 500).apply { name = "Gelb" }
        val s5 = SolidRect(75, 120, Colors.PURPLE).xy(100, 300).apply { name = "Purple" }

        addChild(s1)
        addChild(s2)
        addChild(s3)
        addChild(s4)
        addChild(s5)

        usePhysics()

        addPhysicsComponentsTo(s1, s5) {
            println("Ich ${owner.name} kollidiere mit ${it.owner.name}")
        }
        addPhysicsComponentsTo(s2, s3, s4, isDynamic = false)

        addUpdater {
            if (views.keys[Key.UP]) if (s1.physics?.isGrounded == true) s1.physics?.addForce(Vec2(0.0f, -400.0f))
            if (views.keys[Key.LEFT]) s1.physics?.addForce(Vec2(-10.0f, 0.0f))
            if (views.keys[Key.RIGHT]) s1.physics?.addForce(Vec2(10.0f, 0.0f))
            if (views.keys[Key.W]) if (s5.physics?.isGrounded == true) s5.physics?.addForce(Vec2(0.0f, -400.0f))
            if (views.keys[Key.A]) s5.physics?.addForce(Vec2(-10.0f, 0.0f))
            if (views.keys[Key.D]) s5.physics?.addForce(Vec2(10.0f, 0.0f))
            /*if (views.keys.justPressed(Key.RIGHT)) testPlayer.bus.send(StateTransition(testPlayer.walkState))
            if (views.keys.justPressed(Key.SPACE)) testPlayer.bus.send(StateTransition(testPlayer.jumpState))
            if (views.keys.justReleased(Key.RIGHT)) testPlayer.bus.send(StateTransition(testPlayer.idleState))*/
        }
    }
}