package kr.ac.tukorea.ge.sgp02.a2019182019.morecontrols;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Paint paint = new Paint();
    private Paint textPaint = new Paint();
    private Rect textExtentRect = new Rect();

    private Paint blockPaint = new Paint();
    private Paint pacManPaint = new Paint();
    private Paint foodPaint = new Paint();
    private RectF pacManRectF = new RectF();
    private RectF roadRectF = new RectF();

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint.setColor(Color.BLACK);
        pacManPaint.setColor(Color.YELLOW);
        blockPaint.setColor(Color.BLUE);
        blockPaint.setStyle(Paint.Style.STROKE);
        blockPaint.setStrokeWidth(10);
        foodPaint.setColor(Color.argb(255,255,113,113));
        textPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = width - paddingLeft - paddingRight;
        int contentHeight = height + paddingTop - paddingBottom;

        int size = contentWidth < contentHeight ? contentWidth : contentHeight;

        int centerX = paddingLeft + contentWidth / 2;
        int centerY = paddingTop + contentHeight / 2;
        textPaint.setTextSize(size/20);

        drawBackGround(canvas, paddingLeft, paddingTop, contentWidth, contentHeight);
        drawMiniCircle(canvas, paddingLeft,contentWidth, contentHeight, centerY, size);
        drawCenterText(canvas, contentHeight, centerX, centerY);
        drawPacMan(canvas, paddingLeft, contentWidth, contentHeight,size, centerY);
        drawBlock(canvas, paddingLeft, paddingTop, contentWidth, contentHeight);
        drawRoadBlock(canvas, paddingLeft, contentWidth, contentHeight, size, centerY);

    }

    private void drawRoadBlock(Canvas canvas, int paddingLeft, int contentWidth, int contentHeight, int size, int centerY) {
        int rectTop = centerY + contentHeight /4 - size /10;
        int rectBottom = centerY + contentHeight /4 + size /10;
        roadRectF.set(paddingLeft,rectTop, paddingLeft + contentWidth,rectBottom);
        canvas.drawRect(roadRectF,blockPaint);
    }

    private void drawBackGround(Canvas canvas, int paddingLeft, int paddingTop, int contentWidth, int contentHeight) {
        canvas.drawRoundRect(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight,30,30,paint);
    }

    private void drawPacMan(Canvas canvas, int paddingLeft, int contentWidth,int contentHeight, int size, int centerY) {
        int ballRadius = size / 16;
        int pecManCenterX = paddingLeft + contentWidth / 4;
        int pacManCenterY = centerY + contentHeight /4;
        pacManRectF.set(pecManCenterX-ballRadius,pacManCenterY-ballRadius,pecManCenterX +ballRadius,pacManCenterY+ballRadius);
        canvas.drawArc(pacManRectF,25f, 295f, true, pacManPaint);
    }

    private void drawBlock(Canvas canvas, int paddingLeft, int paddingTop, int contentWidth, int contentHeight) {
        canvas.drawRoundRect(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight,30,30,blockPaint);
    }

    private void drawMiniCircle(Canvas canvas, int paddingLeft, int contentWidth, int contentHeight, int centerY, int size) {
        int circleRadius = size / 40;
        int circleStartX = paddingLeft + contentWidth / 4 - circleRadius;
        int circleStartY = centerY + contentHeight /4;
        int widthVal = size/4;
        int num = (paddingLeft+contentWidth-circleStartX)/widthVal+1;

        for (int i=1; i<num; ++i)
        {
            canvas.drawCircle(circleStartX + widthVal*i, circleStartY, circleRadius, foodPaint);
        }
    }

    private void drawCenterText(Canvas canvas, int contentHeight, int centerX, int centerY) {
        String text ="GAME START";
        textPaint.getTextBounds(text,0,text.length(),textExtentRect);
        int textX = centerX - textExtentRect.width() / 2;
        int textY = centerY - contentHeight/8;
        canvas.drawText(text,textX,textY,textPaint);
    }
}
