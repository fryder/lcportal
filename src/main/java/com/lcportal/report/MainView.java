package com.lcportal.report;

import com.lcportal.report.entity.Summary;
import com.lcportal.report.repository.SummaryRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {

    @Autowired
    private final SummaryRepository summaryRepository;
    final Grid<Summary> grid;

    private final SummaryEditor editor;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(SummaryRepository summaryRepository, SummaryEditor editor) {
        this.summaryRepository = summaryRepository;
        this.editor = editor;
        this.grid = new Grid<>(Summary.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New employee", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("200px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listEmployees(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editSummary(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editSummary(new Summary("", "")));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listEmployees(filter.getValue());
        });

        listEmployees(null);
    }

    void listEmployees(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(summaryRepository.findAll());
        } else {
            grid.setItems(summaryRepository.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
