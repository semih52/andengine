package org.anddev.andengine.opengl.texture.bitmap.source.decorator;

import org.anddev.andengine.opengl.texture.bitmap.source.IBitmapTextureSource;
import org.anddev.andengine.opengl.texture.bitmap.source.decorator.shape.IBitmapTextureSourceDecoratorShape;

import android.graphics.LinearGradient;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 19:21:24 - 05.11.2010
 */
public class LinearGradientFillBitmapTextureSourceDecorator extends BaseShapeBitmapTextureSourceDecorator {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final LinearGradientDirection mLinearGradientDirection;
	protected final int[] mColors;
	protected final float[] mPositions;

	// ===========================================================
	// Constructors
	// ===========================================================

	public LinearGradientFillBitmapTextureSourceDecorator(final IBitmapTextureSource pBitmapTextureSource, final IBitmapTextureSourceDecoratorShape pBitmapTextureSourceDecoratorShape, final int pFromColor, final int pToColor, final LinearGradientDirection pLinearGradientDirection) {
		this(pBitmapTextureSource, pBitmapTextureSourceDecoratorShape, pFromColor, pToColor, pLinearGradientDirection, null);
	}

	public LinearGradientFillBitmapTextureSourceDecorator(final IBitmapTextureSource pBitmapTextureSource, final IBitmapTextureSourceDecoratorShape pBitmapTextureSourceDecoratorShape, final int pFromColor, final int pToColor, final LinearGradientDirection pLinearGradientDirection, final TextureSourceDecoratorOptions pTextureSourceDecoratorOptions) {
		this(pBitmapTextureSource, pBitmapTextureSourceDecoratorShape, new int[] { pFromColor, pToColor }, null, pLinearGradientDirection, pTextureSourceDecoratorOptions);
	}

	public LinearGradientFillBitmapTextureSourceDecorator(final IBitmapTextureSource pBitmapTextureSource, final IBitmapTextureSourceDecoratorShape pBitmapTextureSourceDecoratorShape, final int[] pColors, final float[] pPositions, final LinearGradientDirection pLinearGradientDirection) {
		this(pBitmapTextureSource, pBitmapTextureSourceDecoratorShape, pColors, pPositions, pLinearGradientDirection, null);
	}

	public LinearGradientFillBitmapTextureSourceDecorator(final IBitmapTextureSource pBitmapTextureSource, final IBitmapTextureSourceDecoratorShape pBitmapTextureSourceDecoratorShape, final int[] pColors, final float[] pPositions, final LinearGradientDirection pLinearGradientDirection, final TextureSourceDecoratorOptions pTextureSourceDecoratorOptions) {
		super(pBitmapTextureSource, pBitmapTextureSourceDecoratorShape, pTextureSourceDecoratorOptions);
		this.mColors = pColors;
		this.mPositions = pPositions;
		this.mLinearGradientDirection = pLinearGradientDirection;

		this.mPaint.setStyle(Style.FILL);

		final int right = pBitmapTextureSource.getWidth() - 1;
		final int bottom = pBitmapTextureSource.getHeight() - 1;

		final float fromX = pLinearGradientDirection.getFromX(right);
		final float fromY = pLinearGradientDirection.getFromY(bottom);
		final float toX = pLinearGradientDirection.getToX(right);
		final float toY = pLinearGradientDirection.getToY(bottom);

		this.mPaint.setShader(new LinearGradient(fromX, fromY, toX, toY, pColors, pPositions, TileMode.CLAMP));
	}

	@Override
	public LinearGradientFillBitmapTextureSourceDecorator clone() {
		return new LinearGradientFillBitmapTextureSourceDecorator(this.mBitmapTextureSource, this.mBitmapTextureSourceDecoratorShape, this.mColors, this.mPositions, this.mLinearGradientDirection, this.mTextureSourceDecoratorOptions);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static enum LinearGradientDirection {
		// ===========================================================
		// Elements
		// ===========================================================

		LEFT_TO_RIGHT(1, 0, 0, 0),
		RIGHT_TO_LEFT(0, 0, 1, 0),
		BOTTOM_TO_TOP(0, 1, 0, 0),
		TOP_TO_BOTTOM(0, 0, 0, 1),
		TOPLEFT_TO_BOTTOMRIGHT(0, 0, 1, 1),
		BOTTOMRIGHT_TO_TOPLEFT(1, 1, 0, 0),
		TOPRIGHT_TO_BOTTOMLEFT(1, 0, 0, 1),
		BOTTOMLEFT_TO_TOPRIGHT(0, 1, 1, 0);

		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final int mFromX;
		private final int mFromY;
		private final int mToX;
		private final int mToY;

		// ===========================================================
		// Constructors
		// ===========================================================

		private LinearGradientDirection(final int pFromX, final int pFromY, final int pToX, final int pToY) {
			this.mFromX = pFromX;
			this.mFromY = pFromY;
			this.mToX = pToX;
			this.mToY = pToY;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		final int getFromX(int pRight) {
			return this.mFromX * pRight;
		}

		final int getFromY(int pBottom) {
			return this.mFromY * pBottom;
		}

		final int getToX(int pRight) {
			return this.mToX * pRight;
		}

		final int getToY(int pBottom) {
			return this.mToY * pBottom;
		}

		// ===========================================================
		// Methods from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
