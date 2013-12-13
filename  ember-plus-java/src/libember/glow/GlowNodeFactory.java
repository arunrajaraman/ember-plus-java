package libember.glow;

import libember.ber.Tag;
import libember.ber.Type;
import libember.dom.Node;
import libember.dom.NodeFactory;

/**
 * This class derives from {@link NodeFactory} and is used to create ber encoded
 * objects that are defined in the glow dtd.
 */
public class GlowNodeFactory extends NodeFactory {
	@Override
	public Node createApplicationDefinedNode(Type type, Tag tag) {
		if (type.isApplicationDefined()) {
			final GlowType glowType = GlowType.valueOf(type.value());
			switch (glowType) {
			case COMMAND:
				return new GlowCommand(tag);

			case ELEMENTCOLLECTION:
				return new GlowElementCollection(tag);

			case ROOTELEMENTCOLLECTION:
				return new GlowRootElementCollection(tag);

			case STRINGINTEGERCOLLECTION:
				return new GlowStringIntegerCollection(tag);

			case STRINGINTEGERPAIR:
				return new GlowStringIntegerPair(tag);

			case NODE:
				return new GlowNode(tag);

			case QUALIFIEDNODE:
				return new GlowQualifiedNode(tag);

			case PARAMETER:
				return new GlowParameter(tag);

			case QUALIFIEDPARAMETER:
				return new GlowQualifiedParameter(tag);

			case STREAMCOLLECTION:
				return new GlowStreamCollection(tag);

			case STREAMENTRY:
				return new GlowStreamEntry(tag);

			case STREAMDESCRIPTOR:
				return new GlowStreamDescriptor(tag);

			case MATRIX:
				return new GlowMatrix(tag);

			case TARGET:
				return new GlowTarget(tag);

			case SOURCE:
				return new GlowSource(tag);

			case CONNECTION:
				return new GlowConnection(tag);

			case QUALIFIEDMATRIX:
				return new GlowQualifiedMatrix(tag);

			case LABEL:
				return new GlowLabel(tag);

			case FUNCTION:
				return new GlowFunction(tag);

			case QUALIFIEDFUNCTION:
				return new GlowQualifiedFunction(tag);

			case TUPLEITEMDESCRIPTION:
				return new GlowTupleItemDescription(tag);

			case INVOCATION:
				return new GlowInvocation(tag);

			case INVOCATIONRESULT:
				return new GlowInvocationResult(tag);

			default:
				break;
			}
		}
		return null;
	}

}
