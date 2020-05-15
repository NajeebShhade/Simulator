package expression;

public class Number implements Expression {
	private double Valueue;

	public Number(double Valueue) {
		this.Valueue = Valueue;
	}

	public void setValue(double Valueue) {
		this.Valueue = Valueue;
	}

	@Override
	public double calculate() {
		return Valueue;
	}
}