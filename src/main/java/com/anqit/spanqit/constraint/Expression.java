package com.anqit.spanqit.constraint;

import java.util.ArrayList;
import java.util.function.Function;

import com.anqit.spanqit.core.Assignable;
import com.anqit.spanqit.core.Groupable;
import com.anqit.spanqit.core.Orderable;
import com.anqit.spanqit.core.SpanqitUtils;
import com.anqit.spanqit.core.StandardQueryElementCollection;

/**
 * A SPARQL expression. Used by filters, having clauses, order and group by
 * clauses, assignments, and as arguments to other expressions.
 *
 * @param <T>
 *            the type of Expression (ie, Function or Operation). Used to
 *            support fluency
 *            
 * @see <a
 *      href="http://www.w3.org/TR/2013/REC-sparql11-query-20130321/#termConstraint">SPARQL
 *      Filters</a>
 *      <br>
 *      <a href="http://www.w3.org/TR/2013/REC-sparql11-query-20130321/#having">
 *      SPARQL HAVING</a>
 *      <br>
 *      <a href=
 *      "http://www.w3.org/TR/2013/REC-sparql11-query-20130321/#modOrderBy"
 *      >SPARQL ORDER BY</a>
 *      <br>
 *      <a
 *      href="http://www.w3.org/TR/2013/REC-sparql11-query-20130321/#groupby">
 *      SPARQL GROUP BY</a>
 *      <br>
 *      <a
 *      href="http://www.w3.org/TR/2013/REC-sparql11-query-20130321/#assignment">
 *      SPARQL Assignments</a>
 */
public abstract class Expression<T extends Expression<T>> extends
		StandardQueryElementCollection<Operand> implements Operand,
		Orderable, Groupable, Assignable {
	private static final Function<String, String> WRAPPER = SpanqitUtils::getParenthesizedString;
	
	protected SparqlOperator operator;

	Expression(SparqlOperator operator) {
		this(operator, " " + operator.getQueryString() + " ");
	}

	Expression(SparqlOperator operator, String delimeter) {
		super(delimeter, new ArrayList<Operand>());
		this.operator = operator;
		parenthesize(false);
	}

	@SuppressWarnings("unchecked")
	T addOperand(Operand... operands) {
		addElements(operands);

		return (T) this;
	}

	/**
	 * Indicate that this expression should be wrapped in parentheses
	 * when converted to a query string
	 * 
	 * @return this
	 */
	public T parenthesize() {
		return parenthesize(true);
	}
	
	/**
	 * Indicate if this expression should be wrapped in parentheses
	 * when converted to a query string
	 * 
	 * @param parenthesize
	 * @return this
	 */
	@SuppressWarnings("unchecked")
	public T parenthesize(boolean parenthesize) {
		if(parenthesize) {
			setWrapperMethod(WRAPPER);
		} else {
			resetWrapperMethod();
		}
		
		return (T) this;
	}
	
	Operand getOperand(int index) {
		return ((ArrayList<Operand>) elements).get(index);
	}
}