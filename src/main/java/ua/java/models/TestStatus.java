package ua.java.models;

public enum TestStatus {
	Developing, Expectation, Approved, Processing, Disapproved, ChangeAfter;

	public TestStatus getStatus(String status) {
		switch (status) {
		case "dev":
			return TestStatus.Developing;
		case "exp":
			return TestStatus.Expectation;
		case "app":
			return TestStatus.Approved;
		case "pro":
			return TestStatus.Processing;
		case "dis":
			return TestStatus.Disapproved;
		case "cha":
			return TestStatus.ChangeAfter;
		default:
			return TestStatus.Developing;
		}
	}
}
