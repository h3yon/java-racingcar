package racingcar.domain;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import exception.OutOfRangeException;

public class CarTest {

	@DisplayName("랜덤값 4미만으로 실행시 이동X")
	@ValueSource(ints = {0,1,2,3})
	@ParameterizedTest
	public void When_lessThan4_Expected_0(int randomNumber) {
		// given
		Car car = new Car();
		RandomGenerator randomGenerator = () -> randomNumber;

		// when
		MoveCount moveCount = car.run(randomGenerator.generate());

		// then
		assertThat(moveCount.count()).isEqualTo(0);
	}

	@DisplayName("랜덤값 4이상으로 실행시 1 이동")
	@ValueSource(ints = {4,5,6,7,8,9})
	@ParameterizedTest
	public void When_moreThanOrEqual4_Expected_1(int randomNumber) {
		// given
		Car car = new Car();
		RandomGenerator randomGenerator = () -> randomNumber;

		// when
		MoveCount moveCount = car.run(randomGenerator.generate());

		// then
		assertThat(moveCount.count()).isEqualTo(1);
	}

	@DisplayName("랜덤이 범위를 벗어나면 RunTimeException발생")
	@ValueSource(ints = {-1,10})
	@ParameterizedTest
	public void When_RandomNumberIsOutOfRange_Expected_RunTimeException(int randomNumber) {
		// given
		Car car = new Car();
		RandomGenerator randomGenerator = () -> randomNumber;

		// when, then
		assertThatThrownBy(() -> car.run(randomGenerator.generate()))
			.isInstanceOf(OutOfRangeException.class);
	}
}