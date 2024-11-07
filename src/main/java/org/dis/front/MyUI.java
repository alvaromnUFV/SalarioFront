package org.dis.front;

import javax.servlet.annotation.WebServlet;

import com.ufv.dis.EmpleadoBR;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.w3c.dom.Text;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    private TextField creatLabel (String texto){
        TextField etiqueta = new TextField();
        etiqueta.setCaption(texto);
        return etiqueta;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout salarioBruto = new HorizontalLayout();
        final HorizontalLayout salarioNeto = new HorizontalLayout();
        final VerticalLayout salaioBrutoContenedor = new VerticalLayout();
        final VerticalLayout salaioNetoContenedor = new VerticalLayout();

        TextField tipo = creatLabel("Tipo de empleado");
        TextField ventasMes = creatLabel("Venta del mes");
        TextField horasExtra = creatLabel("Horas extra");

        salarioBruto.addComponents(tipo, ventasMes, horasExtra);

        Button button = new Button("Calcular");
        button.addClickListener(e -> {});

        Button botonSalarioBruto = new Button("Calcula Salario Bruto");
        botonSalarioBruto.addClickListener(e -> {
            String tipoEmpleadoIn = tipo.getValue();
            double ventasMesIn = Double.parseDouble(ventasMes.getValue());
            double horasExtraIn = Double.parseDouble(horasExtra.getValue());

            EmpleadoBR empleado = new EmpleadoBR();

            double resultado = empleado.calculaSalarioBruto(tipoEmpleadoIn, ventasMesIn, horasExtraIn);
        });

        Button botonSalarioNeto = new Button("Calcula Salario Neto");
        botonSalarioNeto.addClickListener(e -> {});

        salaioBrutoContenedor.addComponents(salarioBruto, botonSalarioBruto);
        salaioNetoContenedor.addComponents(salarioNeto, botonSalarioNeto);

        TabSheet tabs = new TabSheet();
        tabs.addTab(salaioBrutoContenedor).setCaption("Calcula Salario Bruto");
        tabs.addTab(salaioNetoContenedor).setCaption("Calcula Salario Neto");

        layout.addComponents(tabs);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}