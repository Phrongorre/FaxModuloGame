package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import bytesmyth.games.edpg.ExecDysfuncPlatformer;
import bytesmyth.games.edpg.level.LevelScreen;
import bytesmyth.games.edpg.util.Assets;

public class Door extends Trigger {
	
	private Class<? extends LevelScreen> nextLevel;
	private Label promptLabel;
	
	public Door(Class<? extends LevelScreen> level, float x, float y, Stage s) {
		super(x, y, s);
		
		this.loadTexture("door.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
		
		this.nextLevel = level;
		this.promptLabel = new Label("UP", Assets.skin);
		this.addActor(this.promptLabel);
		this.promptLabel.setPosition(this.getWidth()/2f, this.getHeight()+10f, Align.bottom);
		this.promptLabel.setVisible(false);
	}
	public Door(Class<? extends LevelScreen> level, Stage s) {
		this(level, 0f, 0f, s);
	}
	
	public void use() {
		if (this.nextLevel == null) return;
		
		try {
			ExecDysfuncPlatformer.setActiveScreen(this.nextLevel.newInstance());
		} catch (Exception e) {}
	}
	
	@Override
	public boolean activate() {
		if (this.promptLabel == null) return false;
		
		this.promptLabel.setVisible(true);
		return true;
	}

	@Override
	public boolean deactivate() {
		if (this.promptLabel == null) return false;
		
		this.promptLabel.setVisible(false);
		return true;
	}

}