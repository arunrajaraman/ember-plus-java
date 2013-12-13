package libember.glow;

import libember.ber.Class;
import libember.ber.Tag;

/**
 * This class contains all tag definitions for all object types used by the
 * Ember+ specification.
 */
final class GlowTags {
	static class Command {
		final static Tag NUMBER = new Tag(Class.CONTEXT, 0);
		final static Tag DIRFIELD_MASK = new Tag(Class.CONTEXT, 1);
		final static Tag INVOCATION = new Tag(Class.CONTEXT, 2);
	}
	static class Connection {
		final static Tag TARGET = new Tag(Class.CONTEXT, 0);
		final static Tag SOURCES = new Tag(Class.CONTEXT, 1);
		final static Tag OPERATION = new Tag(Class.CONTEXT, 2);
		final static Tag DISPOSITION = new Tag(Class.CONTEXT, 3);
	}

	static class Function {
		final static Tag NUMBER = new Tag(Class.CONTEXT, 0);
		final static Tag CONTENTS = new Tag(Class.CONTEXT, 1);
		final static Tag CHILDREN = new Tag(Class.CONTEXT, 2);
	}

	static class FunctionContents {
		final static Tag IDENTIFIER = new Tag(Class.CONTEXT, 0);
		final static Tag DESCRIPTION = new Tag(Class.CONTEXT, 1);
		final static Tag ARGUMENTS = new Tag(Class.CONTEXT, 2);
		final static Tag RESULT = new Tag(Class.CONTEXT, 3);
	}

	static class Invocation {
		final static Tag INVOCATIONID = new Tag(Class.CONTEXT, 0);
		final static Tag ARGUMENTS = new Tag(Class.CONTEXT, 1);
	}

	static class InvocationResult {
		final static Tag INVOCATIONID = new Tag(Class.CONTEXT, 0);
		final static Tag SUCCESS = new Tag(Class.CONTEXT, 1);
		final static Tag RESULT = new Tag(Class.CONTEXT, 2);
	}

	static class Label {
		final static Tag BASEPATH = new Tag(Class.CONTEXT, 0);
		final static Tag DESCRIPTION = new Tag(Class.CONTEXT, 1);
	}

	static class Matrix {
		final static Tag NUMBER = new Tag(Class.CONTEXT, 0);
		final static Tag CONTENTS = new Tag(Class.CONTEXT, 1);
		final static Tag CHILDREN = new Tag(Class.CONTEXT, 2);
		final static Tag TARGETS = new Tag(Class.CONTEXT, 3);
		final static Tag SOURCES = new Tag(Class.CONTEXT, 4);
		final static Tag CONNECTIONS = new Tag(Class.CONTEXT, 5);
	}

	static class MatrixContents {
		final static Tag IDENTIFIER = new Tag(Class.CONTEXT, 0);
		final static Tag DESCRIPTION = new Tag(Class.CONTEXT, 1);
		final static Tag TYPE = new Tag(Class.CONTEXT, 2);
		final static Tag ADDRESSINGMODE = new Tag(Class.CONTEXT, 3);
		final static Tag TARGETCOUNT = new Tag(Class.CONTEXT, 4);
		final static Tag SOURCECOUNT = new Tag(Class.CONTEXT, 5);
		final static Tag MAXIMUMTOTALCONNECTS = new Tag(Class.CONTEXT, 6);
		final static Tag MAXIMUMCONNECTSPERTARGET = new Tag(Class.CONTEXT, 7);
		final static Tag PARAMETERSLOCATION = new Tag(Class.CONTEXT, 8);
		final static Tag GAINPARAMETERNUMBER = new Tag(Class.CONTEXT, 9);
		final static Tag LABELS = new Tag(Class.CONTEXT, 10);
	}

	static class Node {
		final static Tag NUMBER = new Tag(Class.CONTEXT, 0);
		final static Tag CONTENTS = new Tag(Class.CONTEXT, 1);
		final static Tag CHILDREN = new Tag(Class.CONTEXT, 2);
	}

	static class NodeContents {
		final static Tag IDENTIFIER = new Tag(Class.CONTEXT,
				NodeProperty.IDENTIFIER.value());
		final static Tag DESCRIPTION = new Tag(Class.CONTEXT,
				NodeProperty.DESCRIPTION.value());
		final static Tag IS_ROOT = new Tag(Class.CONTEXT,
				NodeProperty.ISROOT.value());
		final static Tag IS_ONLINE = new Tag(Class.CONTEXT,
				NodeProperty.ISONLINE.value());
	}

	static class Parameter {
		final static Tag NUMBER = new Tag(Class.CONTEXT, 0);
		final static Tag CONTENTS = new Tag(Class.CONTEXT, 1);
		final static Tag CHILDREN = new Tag(Class.CONTEXT, 2);
	}

	static class ParameterContents {
		final static Tag IDENTIFIER = new Tag(Class.CONTEXT,
				ParameterProperty.IDENTIFIER.value());
		final static Tag DESCRIPTION = new Tag(Class.CONTEXT,
				ParameterProperty.DESCRIPTION.value());
		final static Tag VALUE = new Tag(Class.CONTEXT,
				ParameterProperty.VALUE.value());
		final static Tag MINIMUM = new Tag(Class.CONTEXT,
				ParameterProperty.MINIMUM.value());
		final static Tag MAXIMUM = new Tag(Class.CONTEXT,
				ParameterProperty.MAXIMUM.value());
		final static Tag ACCESS = new Tag(Class.CONTEXT,
				ParameterProperty.ACCESS.value());
		final static Tag FORMAT = new Tag(Class.CONTEXT,
				ParameterProperty.FORMAT.value());
		final static Tag ENUMERATION = new Tag(Class.CONTEXT,
				ParameterProperty.ENUMERATION.value());
		final static Tag FACTOR = new Tag(Class.CONTEXT,
				ParameterProperty.FACTOR.value());
		final static Tag IS_ONLINE = new Tag(Class.CONTEXT,
				ParameterProperty.ISONLINE.value());
		final static Tag FORMULA = new Tag(Class.CONTEXT,
				ParameterProperty.FORMULA.value());
		final static Tag STEP = new Tag(Class.CONTEXT,
				ParameterProperty.STEP.value());
		final static Tag DEFAULT = new Tag(Class.CONTEXT,
				ParameterProperty.DEFAULT.value());
		final static Tag TYPE = new Tag(Class.CONTEXT,
				ParameterProperty.TYPE.value());
		final static Tag ENUM_MAP = new Tag(Class.CONTEXT,
				ParameterProperty.ENUMMAP.value());
		final static Tag STREAM_IDENTIFIER = new Tag(Class.CONTEXT,
				ParameterProperty.STREAMIDENTIFIER.value());
		final static Tag STREAM_DESCRIPTOR = new Tag(Class.CONTEXT,
				ParameterProperty.STREAMDESCRIPTOR.value());
	}

	static class QualifiedFunction {
		final static Tag PATH = new Tag(Class.CONTEXT, 0);
		final static Tag CONTENTS = new Tag(Class.CONTEXT, 1);
		final static Tag CHILDREN = new Tag(Class.CONTEXT, 2);
	}

	static class QualifiedMatrix {
		final static Tag PATH = new Tag(Class.CONTEXT, 0);
		final static Tag CONTENTS = new Tag(Class.CONTEXT, 1);
		final static Tag CHILDREN = new Tag(Class.CONTEXT, 2);
		final static Tag TARGETS = new Tag(Class.CONTEXT, 3);
		final static Tag SOURCES = new Tag(Class.CONTEXT, 4);
		final static Tag CONNECTIONS = new Tag(Class.CONTEXT, 5);
	}

	static class QualifiedNode {
		final static Tag PATH = new Tag(Class.CONTEXT, 0);
		final static Tag CONTENTS = new Tag(Class.CONTEXT, 1);
		final static Tag CHILDREN = new Tag(Class.CONTEXT, 2);
	}

	static class QualifiedParameter {
		final static Tag PATH = new Tag(Class.CONTEXT, 0);
		final static Tag CONTENTS = new Tag(Class.CONTEXT, 1);
		final static Tag CHILDREN = new Tag(Class.CONTEXT, 2);
	}

	static class Signal {
		final static Tag NUMBER = new Tag(Class.CONTEXT, 0);
	}

	static class StreamDescriptor {
		final static Tag FORMAT = new Tag(Class.CONTEXT, 0);
		final static Tag OFFSET = new Tag(Class.CONTEXT, 1);
	}

	static class StreamEntry {
		final static Tag STREAM_IDENTIFIER = new Tag(Class.CONTEXT, 0);
		final static Tag STREAM_VALUE = new Tag(Class.CONTEXT, 1);
	}

	static class StringIntegerPair {
		final static Tag NAME = new Tag(Class.CONTEXT, 0);
		final static Tag VALUE = new Tag(Class.CONTEXT, 1);
	}

	static class TupleItemDescription {
		final static Tag TYPE = new Tag(Class.CONTEXT, 0);
		final static Tag NAME = new Tag(Class.CONTEXT, 1);
	}

	final static Tag ROOT = new Tag(Class.APPLICATION, 0);

	final static Tag ELEMENT_DEFAULT = new Tag(Class.CONTEXT, 0);
}
