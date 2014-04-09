package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class TabPathology extends Composite {

	PatientServiceAsync service = GWT.create(PatientService.class);

	private static TabPathologyUiBinder uiBinder = GWT
			.create(TabPathologyUiBinder.class);
	@UiField
	CheckBox r_nolesion;
	@UiField
	CheckBox l_nolesion;
	@UiField
	ListBox l_metastasis;
	@UiField
	ListBox r_metastasis;
	@UiField
	ListBox r_lymphnode;
	@UiField
	ListBox l_lymphnode;
	@UiField
	ListBox r_her2;
	@UiField
	ListBox l_her2;
	@UiField
	ListBox r_pgr;
	@UiField
	ListBox l_pgr;
	@UiField
	ListBox r_er;
	@UiField
	ListBox l_er;
	@UiField
	ListBox r_stage;
	@UiField
	ListBox l_stage;
	@UiField
	ListBox r_hpdiag;
	@UiField
	ListBox l_hpdiag;
	@UiField
	AbsolutePanel panel;
	@UiField
	Label r_hpdiag_label;
	@UiField
	Label r_stage_label;
	@UiField
	Label r_er_label;
	@UiField
	Label r_pgr_label;
	@UiField
	Label r_her2_label;
	@UiField
	Label r_lymphnode_label;
	@UiField
	Label r_metastasis_label;
	@UiField
	Label l_metastasis_label;
	@UiField
	Label l_lymphnode_label;
	@UiField
	Label l_her2_label;
	@UiField
	Label l_pgr_label;
	@UiField
	Label l_er_label;
	@UiField
	Label l_stage_label;
	@UiField
	Label l_hpdiag_label;

	interface TabPathologyUiBinder extends UiBinder<Widget, TabPathology> {
	}

	public TabPathology(List<AnswerUI> list) {
		initWidget(uiBinder.createAndBindUi(this));
		r_nolesion.setValue(true);
		l_nolesion.setValue(true);
		initUi();
		MrPoll.updateUi(panel, list);
		onR_nolesionClick(null);
		onL_nolesionClick(null);
	}

	public void initUi() {
		r_stage.addItem("I");
		r_stage.addItem("IIa");
		r_stage.addItem("IIb");
		r_stage.addItem("IIIa");
		r_stage.addItem("IIIb");
		r_stage.addItem("IV");
		l_stage.addItem("I");
		l_stage.addItem("IIa");
		l_stage.addItem("IIb");
		l_stage.addItem("IIIa");
		l_stage.addItem("IIIb");
		l_stage.addItem("IV");
		l_er.addItem("Pozitif");
		l_er.addItem("Negatif");
		r_er.addItem("Pozitif");
		r_er.addItem("Negatif");
		l_pgr.addItem("Pozitif");
		l_pgr.addItem("Negatif");
		r_pgr.addItem("Pozitif");
		r_pgr.addItem("Negatif");
		l_her2.addItem("Pozitif");
		l_her2.addItem("Negatif");
		r_her2.addItem("Pozitif");
		r_her2.addItem("Negatif");
		l_lymphnode.addItem("Pozitif");
		l_lymphnode.addItem("Negatif");
		r_lymphnode.addItem("Pozitif");
		r_lymphnode.addItem("Negatif");
		l_metastasis.addItem("Var");
		l_metastasis.addItem("Yok");
		r_metastasis.addItem("Var");
		r_metastasis.addItem("Yok");
		r_hpdiag.addItem("İnvazif lobüler karsinom");
		r_hpdiag.addItem("Tubuler karsinom");
		r_hpdiag.addItem("İnvazif kribriform karsinom");
		r_hpdiag.addItem("Müsinöz (kolloidal) karsinom");
		r_hpdiag.addItem("Medüller karsinom");
		r_hpdiag.addItem("İnvazif papiller karsinom");
		r_hpdiag.addItem("İnvazif mikropapiller karsinom");
		r_hpdiag.addItem("Sekretuar (jüvenil) karsinom");
		r_hpdiag.addItem("Metaplastik karsinom");
		r_hpdiag.addItem("Nöroendokrin karsinom");
		r_hpdiag.addItem("Apokrin karsinom");
		r_hpdiag.addItem("İnflamatuar karsinom");
		l_hpdiag.addItem("İnvazif lobüler karsinom");
		l_hpdiag.addItem("Tubuler karsinom");
		l_hpdiag.addItem("İnvazif kribriform karsinom");
		l_hpdiag.addItem("Müsinöz (kolloidal) karsinom");
		l_hpdiag.addItem("Medüller karsinom");
		l_hpdiag.addItem("İnvazif papiller karsinom");
		l_hpdiag.addItem("İnvazif mikropapiller karsinom");
		l_hpdiag.addItem("Sekretuar (jüvenil) karsinom");
		l_hpdiag.addItem("Metaplastik karsinom");
		l_hpdiag.addItem("Nöroendokrin karsinom");
		l_hpdiag.addItem("Apokrin karsinom");
		l_hpdiag.addItem("İnflamatuar karsinom");
	}

	@UiHandler("r_nolesion")
	void onR_nolesionClick(ClickEvent event) {
		Boolean lockStatus = !r_nolesion.getValue();
		r_hpdiag.setEnabled(lockStatus);
		r_stage.setEnabled(lockStatus);
		r_er.setEnabled(lockStatus);
		r_pgr.setEnabled(lockStatus);
		r_her2.setEnabled(lockStatus);
		r_lymphnode.setEnabled(lockStatus);
		r_metastasis.setEnabled(lockStatus);
		r_hpdiag_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_stage_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_er_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_pgr_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_her2_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_lymphnode_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_metastasis_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
	}

	@UiHandler("l_nolesion")
	void onL_nolesionClick(ClickEvent event) {
		Boolean lockStatus = !l_nolesion.getValue();
		l_hpdiag.setEnabled(lockStatus);
		l_stage.setEnabled(lockStatus);
		l_er.setEnabled(lockStatus);
		l_pgr.setEnabled(lockStatus);
		l_her2.setEnabled(lockStatus);
		l_lymphnode.setEnabled(lockStatus);
		l_metastasis.setEnabled(lockStatus);
		l_hpdiag_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_stage_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_er_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_pgr_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_her2_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_lymphnode_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_metastasis_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
	}

}
