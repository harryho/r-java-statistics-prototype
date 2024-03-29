package org.rosuda.REngine;

public class REXPFactor extends REXPInteger {
	private String[] levels;
	private RFactor factor;
	
	/** create a new factor REXP
		@param ids indices (one-based!)
		@param levels levels */
	public REXPFactor(int[] ids, String[] levels) {
		super(ids);
		this.levels = (levels==null)?(new String[0]):levels;
		factor = new RFactor(this.payload, this.levels, false, 1);
		attr = new REXPList(
							new RList(
									  new REXP[] {
										  new REXPString(this.levels), new REXPString("factor")
									  }, new String[] { "levels", "class" }));
	}

	/** create a new factor REXP
		@param ids indices (one-based!)
		@param levels levels
		@param attr attributes */
	public REXPFactor(int[] ids, String[] levels, REXPList attr) {
		super(ids, attr);
		this.levels = (levels==null)?(new String[0]):levels;
		factor = new RFactor(this.payload, this.levels, false, 1);
	}
	
	/** create a new factor REXP from an existing RFactor
		@param factor factor object (can be of any index base, the contents will be pulled with base 1) */
	public REXPFactor(RFactor factor) {
		super(factor.asIntegers(1));
		this.factor = factor;
		this.levels = factor.levels();
		attr = new REXPList(
							new RList(
									  new REXP[] {
										  new REXPString(this.levels), new REXPString("factor")
									  }, new String[] { "levels", "class" }));
	}
	
	public REXPFactor(RFactor factor, REXPList attr) {
		super(factor.asIntegers(1), attr);
		this.factor = factor;
		this.levels = factor.levels();
	}

	public boolean isFactor() { return true; }

	/** the factor is guaranteed to have index base 1 */
	public RFactor asFactor() {
		return factor;
	}

	public String[] asStrings() {
		return factor.asStrings();
	}
	
	public String toString() {
		return super.toString()+"["+levels.length+"]";
	}
}
