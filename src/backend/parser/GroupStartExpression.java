package backend.parser;

import backend.BackendController;
import backend.Variable;
import backend.parser.Expression;

/**
 * This class is the implementation of the Group Expression. An instance of this
 * class is created when the parser identifies the beginning of a group - ie the
 * beginning of a command with an unlimited amount of parameters.
 */
public class GroupStartExpression extends MultiExpression {

	public GroupStartExpression(Input info, BackendController controller) {
		super(info, controller, "Group");
		info.incrementIndex();
		Expression command = getBackendController().getParser().parse(info.get(), info.getBreakPoints()).getChildren()
				.get(0);
		command.setParent(this);
		command.addChildren(getBackendController().getParser().parse(getArg(), info.getBreakPoints()).getChildren());
		for (Expression expr : command.getChildren())
			expr.setParent(command);
		this.addChild(command);
	}

	/**
	 * Evaluate the child of this expression with unlimited parameters.
	 * 
	 * @return the variable containing the value that resulted from the
	 *         evaluation
	 */
	@Override
	public Variable evaluate() {
		if (checkLines()) {
			return getChildren().get(0).evaluate();
		}
		return null;
	}

}
