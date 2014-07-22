package pers.aprakash.spanqit.core;

import pers.aprakash.spanqit.rdf.IRI;

/**
 * A SPARQL Prefix declaration
 * 
 * @author Ankit
 *
 * @see <a
 *      href="http://www.w3.org/TR/2013/REC-sparql11-query-20130321/#prefNames">
 *      SPARQL Prefix</a>
 */
public class Prefix implements QueryElement {
	private static final String PREFIX = "PREFIX";
	private String label;
	private IRI iri;

	Prefix(String alias, IRI iri) {
		this.label = alias;
		this.iri = iri;
	}

	@Override
	public String getQueryString() {
		return PREFIX + " " + label + ": " + iri.getQueryString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Prefix)) {
			return false;
		}

		Prefix other = (Prefix) obj;
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		if (iri == null) {
			if (other.iri != null) {
				return false;
			}
		} else if (!iri.equals(other.iri)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((iri == null) ? 0 : iri.hashCode());
		return result;
	}
}