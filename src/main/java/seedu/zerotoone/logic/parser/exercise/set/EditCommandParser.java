package seedu.zerotoone.logic.parser.exercise.set;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_NUM_OF_REPS;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.zerotoone.commons.core.index.Index;
import seedu.zerotoone.logic.commands.exercise.set.EditCommand;
import seedu.zerotoone.logic.commands.exercise.set.EditCommand.EditExerciseSetDescriptor;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.logic.parser.util.ArgumentMultimap;
import seedu.zerotoone.logic.parser.util.ArgumentTokenizer;
import seedu.zerotoone.logic.parser.Parser;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NUM_OF_REPS, PREFIX_WEIGHT);

        Index exerciseId;
        Index setId;

        try {
            String[] splitPreamble = argMultimap.getPreamble().split(" ");
            exerciseId = SetParserUtil.parseIndex(splitPreamble[0]);
            setId = SetParserUtil.parseIndex(splitPreamble[1]);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), e);
        }

        EditExerciseSetDescriptor editExerciseDescriptor = new EditExerciseSetDescriptor();
        if (argMultimap.getValue(PREFIX_NUM_OF_REPS).isPresent()) {
            editExerciseDescriptor.setNumReps(
                SetParserUtil.parseNumReps(argMultimap.getValue(PREFIX_NUM_OF_REPS).get())
            );
        }
        
        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            editExerciseDescriptor.setWeight(
                SetParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get())
            );
        }

        return new EditCommand(exerciseId, setId, editExerciseDescriptor);
    }

}
