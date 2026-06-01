package net.minecraft.util;

public class MouseFilter {

	private float accumulatedInput;   // antes field_76336_a
	private float lastOutput;		 // antes field_76334_b
	private float smoothedValue;	  // antes field_76335_c

	/**
	 * Suaviza el movimiento del mouse
	 */
	public float smooth(float input, float smoothingFactor) {

		// Acumula el input crudo del mouse
		this.accumulatedInput += input;

		// Calcula el delta con respecto al último valor aplicado
		float delta = (this.accumulatedInput - this.lastOutput) * smoothingFactor;

		// Aplica suavizado (interpolación)
		this.smoothedValue += (delta - this.smoothedValue) * 0.5F;

		// Evita overshoot (pasarse del valor suavizado)
		if ((delta > 0.0F && delta > this.smoothedValue) ||
			(delta < 0.0F && delta < this.smoothedValue)) {
			delta = this.smoothedValue;
		}

		// Guarda el output final
		this.lastOutput += delta;

		return delta;
	}

	public void reset() {
		this.accumulatedInput = 0.0F;
		this.lastOutput = 0.0F;
		this.smoothedValue = 0.0F;
	}
}