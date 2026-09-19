package com.mauqa.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*
import android.graphics.drawable.GradientDrawable

class MainActivity : Activity() {
    private lateinit var box: LinearLayout
    private var urdu=false
    private val dark=Color.rgb(17,24,39)
    override fun onCreate(b: Bundle?){super.onCreate(b); showHome()}
    fun dp(n:Int)= (n*resources.displayMetrics.density).toInt()
    fun tv(s:String,size:Float,bold:Boolean=false):TextView=TextView(this).apply{
        text=s;textSize=size;setTextColor(Color.rgb(25,25,28));setPadding(dp(4),dp(5),dp(4),dp(5))
        if(bold) typeface=Typeface.DEFAULT_BOLD
    }
    fun button(s:String, click:()->Unit)=Button(this).apply{text=s;setOnClickListener{click()};isAllCaps=false}
    fun card():LinearLayout=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(dp(18),dp(16),dp(18),dp(16));background=GradientDrawable().apply{setColor(Color.WHITE);cornerRadius=dp(20).toFloat();setStroke(dp(1),Color.rgb(230,230,234))}}
    fun base():LinearLayout{
        val scroll=ScrollView(this); box=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(dp(18),dp(16),dp(18),dp(90))}
        scroll.addView(box); setContentView(LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;addView(scroll,LinearLayout.LayoutParams(-1,0,1f));addView(nav(),LinearLayout.LayoutParams(-1,dp(65)))})
        return box
    }
    fun nav():LinearLayout=LinearLayout(this).apply{
        gravity=Gravity.CENTER;setBackgroundColor(Color.WHITE)
        val a=button(if(urdu)"ہوم" else "Home"){showHome()};val b=button(if(urdu)"انعام" else "Prize"){showPrize()}
        val c=button(if(urdu)"میری انٹریاں" else "My Entries"){showEntries()};val d=button(if(urdu)"فاتحین" else "Winners"){showWinners()}
        addView(a,LinearLayout.LayoutParams(0,dp(60),1f));addView(b,LinearLayout.LayoutParams(0,dp(60),1f));addView(c,LinearLayout.LayoutParams(0,dp(60),1f));addView(d,LinearLayout.LayoutParams(0,dp(60),1f))
    }
    fun header(){box.addView(tv("Mauqa",29,true));box.addView(tv(if(urdu)"ہر انٹری، ایک موقع" else "Har Entry, Ek Mauqa",12))}
    fun showHome(){base();header()
        val lang=button(if(urdu)"English" else "اردو"){urdu=!urdu;showHome()};box.addView(lang)
        val c=card();c.setBackgroundColor(dark);val title=tv(if(urdu)"پریمیم اسمارٹ فون" else "Premium Smartphone",27,true);title.setTextColor(Color.WHITE);c.addView(title)
        val ph=tv("📱",80);ph.gravity=Gravity.CENTER;c.addView(ph)
        val info=tv(if(urdu)"انعام: Rs. 100,000\\nڈیمو انٹری: Rs. 100\\nانٹریاں: 1,247 / 2,000" else "Prize: Rs. 100,000\\nDemo Entry: Rs. 100\\nEntries: 1,247 / 2,000",16);info.setTextColor(Color.WHITE);c.addView(info)
        val bt=button(if(urdu)"انعام دیکھیں" else "View Prize"){showPrize()};c.addView(bt);box.addView(c,LinearLayout.LayoutParams(-1,LinearLayout.LayoutParams.WRAP_CONTENT))
        box.addView(tv(if(urdu)"یہ صرف Step 1 demo ہے۔ کوئی حقیقی ادائیگی یا paid draw فعال نہیں ہے." else "This is the Step 1 demo. No real payment or paid draw is active.",14))
    }
    fun showPrize(){base();header();box.addView(tv(if(urdu)"پریمیم اسمارٹ فون" else "Premium Smartphone",24,true));val c=card();val p=tv("📱",90);p.gravity=Gravity.CENTER;c.addView(p);c.addView(tv(if(urdu)"انعام کی مالیت: Rs. 100,000" else "Prize value: Rs. 100,000",18,true));c.addView(tv(if(urdu)"صرف ڈیمو — کوئی حقیقی ادائیگی نہیں۔" else "Demo only — no real payment.",14));c.addView(button(if(urdu)"ڈیمو انٹری" else "Demo Entry"){Toast.makeText(this,if(urdu)"ڈیمو: ادائیگی فعال نہیں۔" else "Demo: payment is not active.",Toast.LENGTH_SHORT).show()});box.addView(c)}
    fun showEntries(){base();header();box.addView(tv(if(urdu)"میری انٹریاں" else "My Entries",24,true));val c=card();c.addView(tv("🎟️ MQ-001247",17));c.addView(tv("🎟️ MQ-001248",17));c.addView(tv(if(urdu)"صرف ٹیسٹنگ کے لیے نمونہ انٹریاں۔" else "Sample entries for testing only.",14));box.addView(c)}
    fun showWinners(){base();header();box.addView(tv(if(urdu)"فاتحین" else "Winners",24,true));val c=card();c.addView(tv(if(urdu)"پچھلا ڈیمو ڈرا" else "Previous Demo Draw",18,true));c.addView(tv("Winning Entry: MQ-000981",15));box.addView(c)}
}