package ViewWindow;


import Service.ServiceProxy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Emp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

//Client UI window
public class ClientWindow extends JFrame {

    private JMenu catSearch =new JMenu("Search by:");
    private JMenuBar catMenu = new JMenuBar();
    private JTextField txtSearch = new JTextField();
    private JButton btnSearch = new JButton("search");
    private JTextArea txtResults = new JTextArea();

    private ServiceProxy proxy = null;
    private Gson gson = null;

    private final String EMP_NAME = "name",EMP_SALARY ="salary",EMP_ID = "employeeID";
    public ClientWindow(ServiceProxy serviceProxy) {

        this.proxy = serviceProxy;
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        Box north = setNorthBox(), center = setCenterBox() ;

        Container mainContainer = getContentPane();
        mainContainer.add( north , "North" );
        mainContainer.add( center , "Center" );

        btnSearch.addActionListener( new SearchHandler());

        setBounds( 100 , 100 , 600 , 600 );
        setResizable( false );

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                proxy.close();
                System.exit(0);
            }
        });
    }

    private Box setCenterBox() {
        Box center = Box.createVerticalBox();
        Border titleResults = BorderFactory.createTitledBorder("Results");
        center.setBorder(titleResults);
        JScrollPane scrollPane = new JScrollPane( txtResults );
        center.add( scrollPane );
        return center;
    }

    private Box  setNorthBox() {
        Box north = Box.createHorizontalBox();
        north.add( txtSearch );
        north.add( btnSearch );
        north.add(catMenu);
        setTypeMenu();
        return north;
    }

    private void setTypeMenu() {
        catMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        catMenu.setAlignmentY(Component.CENTER_ALIGNMENT);
        catMenu.add(catSearch);
        catSearch.add(new MenuItem(EMP_NAME));
        catSearch.add(new MenuItem(EMP_SALARY));
        catSearch.add(new MenuItem(EMP_ID));
    }

    class SearchHandler implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            String textPattern = txtSearch.getText();
            try{
                List<Emp> empList = proxy.fetchResults(textPattern,catSearch.getText());
                String resultJson = gson.toJson(empList);
                txtResults.setText(resultJson);
            }catch (Exception exp){
                System.out.println( exp );
            }
        }
    }

    private class MenuItem extends JMenuItem implements ActionListener{

        public MenuItem(String text){
            super(text);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case EMP_NAME:
                    catSearch.setText(EMP_NAME);
                    break;
                case EMP_SALARY:
                    catSearch.setText(EMP_SALARY);
                    break;
                case EMP_ID:
                    catSearch.setText(EMP_ID);
                    break;
            }
        }
    }
}
