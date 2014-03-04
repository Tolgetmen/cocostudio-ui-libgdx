package cocostudio.ui.parser.widget;

import cocostudio.ui.CocoStudioUIEditor;
import cocostudio.ui.model.CCOption;
import cocostudio.ui.model.CCWidget;
import cocostudio.ui.parser.WidgetParser;
import cocostudio.ui.util.FontUtil;
import cocostudio.ui.widget.TTFLabelStyle;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class CCTextField extends WidgetParser {

	@Override
	public String getClassName() {
		return "TextField";
	}

	@Override
	public Actor parse(CocoStudioUIEditor editor, CCWidget widget,
			CCOption option) {

		final TTFLabelStyle labelStyle = editor.createLabelStyle(option);

		TextFieldStyle style = new TextFieldStyle(labelStyle.font,
				labelStyle.fontColor, null, null, null);
		TextField textField = new TextField(option.getText(), style) {

			@Override
			public void setText(String text) {
				String sumText = text + getMessageText();

				getStyle().font = FontUtil.createFont(
						labelStyle.getFontFileHandle(), sumText,
						labelStyle.getFontSize());

				super.setText(text);
			};

			@Override
			public void setMessageText(String messageText) {

				String sumText = messageText + getText();

				getStyle().font = FontUtil.createFont(
						labelStyle.getFontFileHandle(), sumText,
						labelStyle.getFontSize());
				super.setMessageText(messageText);
			};
		};
		textField.setMessageText(option.getPlaceHolder());
		textField.setPasswordMode(option.isPasswordEnable());
		textField.setPasswordCharacter(option.getPasswordStyleText());
		return textField;
	}
}
