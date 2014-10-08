package customEnum;

import java.awt.Color;

public enum Status {
	NEW(Color.YELLOW), PROCESSING(Color.ORANGE), FAILED(Color.RED), ACCEPTED(Color.GREEN), REJECTED(Color.GREEN);
	
	private Color color = Color.WHITE;
	
	private Status(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
}
