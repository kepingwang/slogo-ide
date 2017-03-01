package backend.commands;

import java.util.List;

import backend.BackendController;
import backend.Command;
import backend.Variable;

public class IfElseCommand extends Command {

	public IfElseCommand(BackendController controller) {
		super(controller, 3);
	}

	@Override
	public double execute() {
		List<Variable> args = getArgs();
		double ret = 0;
		if (args.get(0).getValue() != 0) {
			ret = getBackendController().getParser().parse(args.get(1).getInfo());
		} else {
			ret = getBackendController().getParser().parse(args.get(2).getInfo());
		}
		return ret;
	}

}