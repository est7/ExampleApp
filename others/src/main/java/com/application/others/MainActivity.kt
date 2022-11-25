package com.application.others

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.application.others.databinding.ActivityMainBinding
import com.application.others.recyclerview.SimpleRecycleViewActivity
import com.example.base.binding
import com.example.base.startActivity

class MainActivity : AppCompatActivity() {
    private val binding by binding<ActivityMainBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }


    private fun initView() {
        binding.sampleRecycleview.setOnClickListener {
            startActivity<SimpleRecycleViewActivity>() }
    }


    /* private fun initView() {
         btn_room.setOnClickListener { startActivity(RoomActivity::class.java) }
         btn_llive.setOnClickListener { startActivity(LiveDataActivity1::class.java) }
         btn_window.setOnClickListener { startActivity<WindowActivity>() }
         btn_touch_event.setOnClickListener { startActivity<TouchEventActivity>() }
         btn_selector.setOnClickListener { startActivity(SelectorDemoActivity::class.java) }
         btn_anim.setOnClickListener { startActivity(AnimActivity::class.java) }
         btn_view_pager.setOnClickListener { startActivity(ViewPagerActivity::class.java) }
         btn_navigation.setOnClickListener { startActivity(NavigationActivity::class.java) }
         btn_drawer_layout.setOnClickListener { startActivity(DrawerLayoutActivity::class.java) }
         btn_constraint_layout.setOnClickListener { startActivity(ConstraintLayoutActivity::class.java) }
         btn_tab_layout.setOnClickListener { startActivity(TableLayoutActivity::class.java) }
         btn_dialog.setOnClickListener { startActivity(DialogActivity::class.java) }
         btn_coordinate_layout.setOnClickListener { startActivity(CoordinateLayoutActivity::class.java) }
         btn_rx_binding.setOnClickListener { startActivity(LoginActivity::class.java) }
         btn_surface_view.setOnClickListener { startActivity(SurfaceViewActivity::class.java) }
         btn_kotlin_activity.setOnClickListener { startActivity<KotlinActivity>() }
         btn_kotlin_example.setOnClickListener { startActivity(KotlinExample::class.java) }
         btn_vs.setOnClickListener { startActivity(ViewStubActivity::class.java) }
         btn_transparent_fragment.setOnClickListener { startActivity(TransparentFragmentActivity::class.java) }
         btnOverrideProperty.setOnClickListener { startActivity(OverridePropertyActivity::class.java) }
         btnKotlinCollection.setOnClickListener { startActivity(KotlinCollectionActivity::class.java) }
         btnInvoke.setOnClickListener { startActivity(InvokeActivity::class.java) }
         btn_anonymous_fun.setOnClickListener { startActivity(AnoymousFunActivity::class.java) }
         btn_constraint_set_layout.setOnClickListener { startActivity(TransitionManagerActivity::class.java) }
         btn_tag_activity.setOnClickListener { startActivity(TagActivity::class.java) }
         btn_tag_view_activity.setOnClickListener { startActivity(TagViewActivity::class.java) }
         btn_constraint_add.setOnClickListener { startActivity(ConstraintLayoutActivity2::class.java) }
         btn_tag_textview.setOnClickListener { startActivity(TagTextViewActivity::class.java) }
         btn_ipc.setOnClickListener { startActivity(RemoteDynamicProxyActivity::class.java) }
         btn_coroutine.setOnClickListener { startActivity(CoroutineActivity::class.java) }
         btn_spannable.setOnClickListener { startActivity(SpannableActivity::class.java) }
         btn_concurrent.setOnClickListener { startActivity(ConcurrentActivity::class.java) }
         btn_webview.setOnClickListener { startActivity(WebViewActivity::class.java) }
         btn_list.setOnClickListener { startActivity(ListActivity::class.java) }
         btn_threadpool.setOnClickListener { startActivity(ThreadPoolActivity::class.java) }
         btn_output_stream.setOnClickListener { startActivity(FileActivity::class.java) }
         btn_threadpool.setOnClickListener { startActivity(ThreadPoolActivity::class.java) }
         btn_output_stream.setOnClickListener { startActivity(FileActivity::class.java) }
         btn_treasure.setOnClickListener { startActivity(TreasureActivity::class.java) }
         btn_workmanager.setOnClickListener { startActivity(WorkManagerActivity::class.java) }
         btn_gson.setOnClickListener { startActivity(GsonActivity::class.java) }
         btn_sp.setOnClickListener { startActivity(SharedPreferenceActivity::class.java) }
         btn_broadcat.setOnClickListener { startActivity(BroadcastActivity::class.java) }
         btn_delegate.setOnClickListener { startActivity(DelegateActivity::class.java) }
         btn_operator.setOnClickListener { startActivity(OverrideOperatorActivity::class.java) }
         btn_vp2.setOnClickListener { startActivity<ViewPager2Activity>() }
         btn_flow.setOnClickListener { startActivity<FlowActivity>() }
         btn_constraintlayou.setOnClickListener { startActivity<ConstraintLayoutActivity3>() }
         btn_factory2.setOnClickListener { startActivity<Factory2Activity>() }
         btn_factory22.setOnClickListener { startActivity<Factory2Activity2>() }
         btn_data_binding.setOnClickListener { startActivity<DataBindingActivity>() }
         btnTransformations.setOnClickListener { startActivity<LiveDataActivity>() }
         btnRetrofit.setOnClickListener { startActivity<test.taylor.com.taylorcode.retrofit.viewmodel.RetrofitActivity>() }
         godActivity.setOnClickListener { startActivity<GodActivity>() }
         repository_single.setOnClickListener { startActivity<RetrofitActivity>() }
         repository_livedata.setOnClickListener { startActivity<test.taylor.com.taylorcode.retrofit.repository_livedata.RetrofitActivity>() }
         presenter.setOnClickListener { startActivity<test.taylor.com.taylorcode.retrofit.presenter.RetrofitActivity>() }
         btn_no_field.setOnClickListener { startActivity<NoFieldActivity>() }
         btnViewFlipper.setOnClickListener { startActivity<ViewFlipperActivity>() }
         btnCoroutineSample.setOnClickListener { startActivity<HandlerThreadVsCoroutineActivity>() }
         btnSuspendCrossActivities.setOnClickListener { startActivity<SplashActivity>() }
         btnCancelCoroutine.setOnClickListener { startActivity<CoroutineCancelActivity>() }
         btnSuspendCoroutine.setOnClickListener { startActivity<SuspendCoroutineActivity>() }
         btnDarkActivity.setOnClickListener { startActivity<DarkActivity>() }
         btnLifecycleActivity.setOnClickListener { startActivity<LifecycleActivity>() }
         btnChannelActivity.setOnClickListener { startActivity<ChannelActivity>() }
         btnDynamic.setOnClickListener { startActivity<DynamicalLayoutActivity>() }
         btnDns.setOnClickListener { startActivity<DnsActivity>() }
         btnMaskViewGroup.setOnClickListener { startActivity<TestMaskActivity>() }
         btnProgressBar.setOnClickListener { startActivity<ProgressBarActivity>() }
         btnVarietyAdapter.setOnClickListener { startActivity<VarietyAdapterActivity>() }
         btnSelectRecyclerView.setOnClickListener { startActivity<SelectRecycleViewActivity>() }
         btnRecyclerViewIndicator.setOnClickListener { startActivity<IndicatorActivity>() }
         btnTypeParameter.setOnClickListener { startActivity<TypeParameterActivity>() }
         btn_new_activity_result.setOnClickListener { startActivity<NewActivityResultActivity>() }
         btnPpath.setOnClickListener { startActivity<PathActivity>() }
         btnLiveComment.setOnClickListener { startActivity<LiveCommentActivity>() }
         btnAddViewActivity.setOnClickListener { startActivity<AddViewActivity>() }
         btn_livecomment.setOnClickListener { startActivity<LaneViewActivity>() }
         btnTransition.setOnClickListener { startActivity<test.taylor.com.taylorcode.ui.anim.transitionmanager.TransitionManagerActivity>() }
         btn_constraintlayout4.setOnClickListener { startActivity<ConstraintLayout4>() }
         btn_kotlin_flow.setOnClickListener { startActivity<test.taylor.com.taylorcode.kotlin.coroutine.flow.FlowActivity>() }
         btnBlur.setOnClickListener { startActivity<BlurActivity>() }
         btnBlur2.setOnClickListener { startActivity<BlurActivity2>() }
         btnCrop.setOnClickListener { startActivity<CropActivity>() }
         btnCrop2.setOnClickListener { startActivity<ShaderActivity>() }
         btnRecyclerViewPerformance.setOnClickListener { startActivity<RecyclerViewPerformanceActivity>() }
         btnPercentLayout.setOnClickListener { startActivity<PercentActivity>() }
         btnInterView.setOnClickListener { startActivity<InterViewActivity>() }
         btnTimePicker.setOnClickListener { startActivity<TimePickerActivity>() }
         btn_touch_delegate.setOnClickListener { startActivity<TouchDelegateActivity>() }
         btn_coordinate_layout2.setOnClickListener { startActivity<NestedScrollCoordinateLayoutActivity>() }
         recyclerview_item_anim.setOnClickListener { startActivity<RecyclerViewItemAnimActivity> { } }
         oneViewGroup.setOnClickListener { startActivity<OneActivity> { } }
         btnMultiTouchDelegate.setOnClickListener { startActivity<test.taylor.com.taylorcode.ui.touch_event.touch_delegate.TouchDelegateActivity> { } }
         poorDialogFragment.setOnClickListener { PoorDialogFragment.show(this@MainActivity) }
         btnNotificationActivity.setOnClickListener { startActivity<NotificationActivity> { } }
         btnAudioRecorder.setOnClickListener { startActivity<AudioRecorderActivity> { } }
         btnAudioEncoder.setOnClickListener { startActivity<HWRecorderActivity> { } }
         btnCoroutine.setOnClickListener { startActivity<CoroutineActivity2> { } }
         count_down_latch.setOnClickListener { startActivity<CountDownLatchActivity> { } }
         dutyChain.setOnClickListener { startActivity<ResponsibilityChainActivity> { } }
         RxSingle.setOnClickListener { startActivity<SingleActivity> { } }
         LaneLayoutManager.setOnClickListener { startActivity<LaneLayoutManagerActivity> { } }
         gridlayout.setOnClickListener { startActivity<GridLayoutActivity> { } }
         kotlinActivity2.setOnClickListener { startActivity<KotlinActivity2> { } }
         btn_vp3.setOnClickListener { startActivity<ViewPager2Activity2> { } }
         btn_overlap.setOnClickListener { startActivity<RecyclerViewAnimActivity> { } }
         btn_item_animator.setOnClickListener { startActivity<RecyclerViewItemAnimatorActivity> { } }
         btn_overlap_anim.setOnClickListener { startActivity<OverlapAnimActivity> { } }
         btn_imageView_scaleType.setOnClickListener { startActivity<ImageViewScaleTypeActivity> { } }
         btn_zoom.setOnClickListener { startActivity<ZoomActivity> { } }
         btn_okio.setOnClickListener { startActivity<OkioActivity> { } }
         btn_log.setOnClickListener { startActivity<LogActivity> { } }
         btn_concurrent_list.setOnClickListener { startActivity<test.taylor.com.taylorcode.aysnc.concurrent.ConcurrentActivity> { } }
         btn_test.setOnClickListener { startActivity<HookSystemServiceActivity> { } }
         btnStickyFragment.setOnClickListener { startActivity<StickyLiveDataActivity> { } }
         btnStateFlow.setOnClickListener { startActivity<StateFlowActivity> { } }
         annotation.setOnClickListener { startActivity<AnnotationActivity2> { } }
         coroutineException.setOnClickListener { startActivity<CoroutineActivity3> { } }
         btnExceptionCoroutine.setOnClickListener { startActivity<CoroutineExceptionActivity> { } }
         btnStructConcurrency.setOnClickListener { startActivity<StructureConcurrencyActivity> { } }
         continuation.setOnClickListener { startActivity<test.taylor.com.taylorcode.kotlin.coroutine.mvvm.SuspendCoroutineActivity> { } }
         coroutineJob.setOnClickListener { startActivity<CoroutineJobActivity> { } }
         flowBackPressure.setOnClickListener { startActivity<FlowActivity2> { } }
         sharedFlow.setOnClickListener { startActivity<SharedFlowActivity> { } }
         btn_glide.setOnClickListener { startActivity<GlideActivity> { } }
         btn_glide_performance.setOnClickListener { startActivity<GlideActivity3> { } }
         btnConcurrentInit.setOnClickListener { startActivity<ConcurrentInitActivity> { } }
         btnChannelActivity2.setOnClickListener { startActivity<test.taylor.com.taylorcode.kotlin.coroutine.channel.ChannelActivity> { } }
         btn_collapsing.setOnClickListener { startActivity<CollapsingToolBarLayoutActivity> { } }
         btnTriangle.setOnClickListener { startActivity<DrawTriangleActivity> { } }
         bottom_navigation_view.setOnClickListener { startActivity<BottomNavigationViewActivity> { } }
         btn_constraintLayout_flow.setOnClickListener { startActivity<ConstraintLayoutFlowActivity> { } }
         btn_flow_lifecycle.setOnClickListener { startActivity<FlowLifecycleActivity> { } }
         btn_fragment_communicate.setOnClickListener { startActivity<FragmentActivity> { } }
     }*/

    private fun startActivity(cls: Class<*>) {
        //kotlin case:start activity by intent
        Intent(this, cls).apply {
            putExtra("data2", "spring2")
        }.also {
            startActivity(it)
        }
    }

}