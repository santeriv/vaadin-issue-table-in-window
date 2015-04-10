package com.profitsoftware.vaadintest;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Theme("valo")
@Push(transport= Transport.STREAMING,value= PushMode.AUTOMATIC)
public class AppUI extends UI {

  private static final long serialVersionUID = 1L;
	private Table table;
  private BeanItemContainer<Bean> container = new BeanItemContainer<Bean>(Bean.class);
  public/*private cannot be accessed by reflection*/ class Bean implements Serializable {
    private String test1;
    private String test2;
    private String test3;

    public Bean(String test1, String test2, String test3) {
      this.test1 = test1;
      this.test2 = test2;
      this.test3 = test3;
    }

    public String getTest1() {
      return test1;
    }

    public void setTest1(String test1) {
      this.test1 = test1;
    }

    public String getTest2() {
      return test2;
    }

    public void setTest2(String test2) {
      this.test2 = test2;
    }

    public String getTest3() {
      return test3;
    }

    public void setTest3(String test3) {
      this.test3 = test3;
    }
  }

  public AppUI() {
    super();
  }

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    setSizeFull();
    table = new Table();
    table.setWidth("100%");
    table.setHeightUndefined();
    table.setPageLength(0);
    table.addStyleName(ValoTheme.TABLE_COMPACT);
    table.setContainerDataSource(container);

    Button openWindowButton = new Button("Open window");
    openWindowButton.setId("openWindowButton");
    openWindowButton.addClickListener(new Button.ClickListener() {
      public void buttonClick(Button.ClickEvent event) {
        container.removeAllItems();
        //set data to table
        setDataToDatasource();
        //do some layouting here also
        Label windowTitle1 = new Label("Window Title 1");
        Label windowTitle2 = new Label("Window Title 2");
        HorizontalLayout windowTitleLayout = new HorizontalLayout(windowTitle1,windowTitle2);
        windowTitleLayout.setWidth("100%");
        windowTitleLayout.setHeightUndefined();

        VerticalLayout windowLayout = new VerticalLayout();
        windowLayout.setSpacing(true);
        windowLayout.setMargin(true);
        windowLayout.setWidth("100%");
        windowLayout.setHeightUndefined();
        windowLayout.addComponent(windowTitleLayout);
        windowLayout.addComponent(table);
        Window window = new Window();
        window.setId("tableWindow");
        window.setWidth("50%");
        window.setHeightUndefined();
        window.setModal(true);
        window.setResizable(false);
        window.center();
        window.setContent(windowLayout);
        Button windowActionButton = new Button("do something");
        HorizontalLayout windowButtonsLayout = new HorizontalLayout(windowActionButton);
        windowButtonsLayout.setWidth("100%");
        windowButtonsLayout.setHeightUndefined();
        windowButtonsLayout.setComponentAlignment(windowActionButton, Alignment.MIDDLE_RIGHT);
        windowLayout.addComponent(windowButtonsLayout);
        windowLayout.setExpandRatio(table,1);
        addWindow(window);
      }
    });
    VerticalLayout uiLayout = new VerticalLayout();
    uiLayout.setSizeFull();
    uiLayout.addComponent(openWindowButton);
    uiLayout.setComponentAlignment(openWindowButton,Alignment.MIDDLE_CENTER);
    setContent(uiLayout);
  }

  private void setDataToDatasource() {
    List<Bean> beans = new ArrayList<Bean>();
    for (int i = 0; i < 10; i++) {
      beans.add(new Bean("ZZZZZZZ-" + i, "YYYY-" + i,"AAAAAAA-" + i));
    }
    container.addAll(beans);
  }

}
