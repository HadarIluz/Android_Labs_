package com.example.ex5x;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

public class MainActivity extends AppCompatActivity implements FragA.FragAListener, FragB.FragBListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragB fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");

		if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)){
			if (fragB != null) {
				getSupportFragmentManager().beginTransaction()
						.show(fragB)
						.commit();
			}
			else {
				getSupportFragmentManager().beginTransaction()
						.add(R.id.fragContainer, FragB.class,null, "FRAGB")
						.commit();
			}
			getSupportFragmentManager().executePendingTransactions();
		}
	}


	@Override
	public void OnClickEvent() {
		FragB fragB;
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			getSupportFragmentManager().beginTransaction()
					.setReorderingAllowed(true)
					.add(R.id.fragContainer, FragB.class, null,"FRAGB")
					.addToBackStack("BBB")
					.commit();
			getSupportFragmentManager().executePendingTransactions();
		}
		fragB = (FragB) getSupportFragmentManager().findFragmentByTag("FRAGB");
		fragB.onNewClick();
	}

}
