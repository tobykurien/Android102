package com.myfamily;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class PersonListActivity extends FragmentActivity implements PersonListFragment.Callbacks, TabListener {

   private boolean mTwoPane;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_person_list);

      if (findViewById(R.id.person_detail_container) != null) {
         mTwoPane = true;
         ((PersonListFragment) getSupportFragmentManager().findFragmentById(R.id.person_list)).setActivateOnItemClick(true);
      }

      ActionBar ab = getActionBar();
      ab.setDisplayShowTitleEnabled(false);
//      ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//      
//      String[] tabs = getResources().getStringArray(R.array.tabs);
//      for (String tab : tabs) {
//         Tab abTab = ab.newTab().setText(tab).setTabListener(this);
//         ab.addTab(abTab);
//      }
   }

   @Override
   protected void onStart() {
      // TODO Auto-generated method stub
      super.onStart();
   }
   
   @Override
   public void onItemSelected(String id) {
      if (mTwoPane) {
         Bundle arguments = new Bundle();
         arguments.putString(PersonDetailFragment.ARG_ITEM_ID, id);
         PersonDetailFragment fragment = new PersonDetailFragment();
         fragment.setArguments(arguments);
         getSupportFragmentManager().beginTransaction().replace(R.id.person_detail_container, fragment).commit();

      } else {
         Intent detailIntent = new Intent(this, PersonDetailActivity.class);
         detailIntent.putExtra(PersonDetailFragment.ARG_ITEM_ID, id);
         startActivity(detailIntent);
      }
   }

   @Override
   public void onTabReselected(Tab tab, FragmentTransaction ft) {
      // TODO Auto-generated method stub

   }

   @Override
   public void onTabSelected(Tab tab, FragmentTransaction ft) {
      //Toast.makeText(this, tab.getText(), Toast.LENGTH_SHORT).show();
   }

   @Override
   public void onTabUnselected(Tab tab, FragmentTransaction ft) {
      // TODO Auto-generated method stub

   }
}
