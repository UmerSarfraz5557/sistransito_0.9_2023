package net.sistransito.mobile.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import net.sistransito.mobile.ait.TabConductorSubTitleFragment;
import net.sistransito.mobile.ait.TabAddressSubTitleFragment;
import net.sistransito.mobile.ait.TabGenerationSubTitleFragment;
import net.sistransito.mobile.ait.TabInfrationSubTitleFragment;
import net.sistransito.mobile.ait.TabVehicleSubTitleFragment;

public class TabAitSubTitleSectionsPagerAdapter extends FragmentPagerAdapter {

	@Override
	public void finishUpdate(ViewGroup container) {
		super.finishUpdate(container);
	}

	@Override
	public void startUpdate(ViewGroup container) {
		super.startUpdate(container);
	}

	public TabAitSubTitleSectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
  		case 0:
  			return TabVehicleSubTitleFragment.newInstance();
  		case 1:
  			return TabConductorSubTitleFragment.newInstance();
  		case 2:
  			return TabAddressSubTitleFragment.newInstance();
  		case 3:
  			return TabInfrationSubTitleFragment.newInstance();
  		case 4:
  			return TabGenerationSubTitleFragment.newInstance();
  		}
  		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 5;
	}
}