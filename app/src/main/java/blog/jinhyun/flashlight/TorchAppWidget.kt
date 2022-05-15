package blog.jinhyun.flashlight

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

/**
 * AppWidgetProvider 브로드캐스트 리시버 클래스 상속.
 */
class TorchAppWidget : AppWidgetProvider() {

    /**
     * 위젯이 업데이트 될 때 호출되는 함수.
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // 위젯이 여러 개 배치되었다면 모든 위젯을 업데이트하는 반복문
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    /**
     * 위젯이 처음 생성될 때 호출되는 함수.
     *
     * @param context
     */
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    /**
     * 위젯이 여러 개일 경우 마지막 위젯이 제거될 때 호출되는 함수.
     *
     * @param context
     */
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

/**
 * 위젯을 업데이트 할 때 호출되는 함수.
 *
 * @param context
 * @param appWidgetManager
 * @param appWidgetId
 */
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // 위젯에 배치하는 뷰가 따로 있는데 RemoteViews 객체로 가져올 수 있음
    val views = RemoteViews(context.packageName, R.layout.torch_app_widget)
    // RemoteViews 객체용으로 준비된 텍스트값을 변경하는 함수
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // 위젯을 업데이트하는 함수
    appWidgetManager.updateAppWidget(appWidgetId, views)
}