package com.seejoke.net.form;

import com.seejoke.net.CallListener;
import com.seejoke.net.LocalServer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 启动主类
 *
 * @author yangzhongying
 * @version 1.0
 * @date 2018-06-01 16:43
 **/
public class Application extends BaseForm {

    private static final long serialVersionUID = -8577615925651575124L;

    private JTextField txtHost;

    private JTextArea txtConsole;

    private JButton btnAction;

    private JLabel lblStatus;

    private LocalServer server;

    private JPanel panelSetting;

    private JScrollPane panelConsole;

    private JLabel labelOne;

    private JTextField txtDomain;

    private JLabel lblwezozcom;

    private JLabel lablTraffic;

    private JLabel labelTwo;

    private JLabel lblSpeed;

    public Application() {
        setTitle("Net-HTTP内网穿透-交流群:15365464");
        this.setSize(546, 432);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("状态：");
        label.setBounds(20, 27, 61, 16);
        getContentPane().add(label);

        lblStatus = new JLabel("服务停止");
        lblStatus.setBounds(60, 27, 280, 16);
        getContentPane().add(lblStatus);

        btnAction = new JButton("启动服务");
        btnAction.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("启动服务".equals(btnAction.getText())) {
                    try {
                        String host = txtHost.getText();
                        server = new LocalServer();
                        server.setServer("http://seejoke.com:3001");
                        server.setForward(host);
                        server.setDomain(txtDomain.getText());
                        server.setCallListener(new CallListener() {

                            @Override
                            public void statusCall(String info) {
                                lblStatus.setText(info);
                            }

                            @Override
                            public void eventCall(String info) {
                                txtConsole.append(info + "\n");
                            }

                            @Override
                            public void onClose() {
                                server.stop();
                                lblStatus.setText("服务停止");
                                btnAction.setText("启动服务");
                            }

                            @Override
                            public void trafficCall(long traffic) {

                                lablTraffic.setText(formatNumber(traffic));
                            }

                            @Override
                            public void speedCall(long speed) {
                                lblSpeed.setText(formatNumber(speed) + "/s");

                            }

                            @Override
                            public void ping(long ms) {
                                lblPing.setText(ms + "ms");
                            }
                        });
                        server.start();
                        btnAction.setText("停止服务");
                    } catch (Exception ex) {
                        lblStatus.setText("启动失败！请检查地址是否正确");
                    }
                } else {
                    server.stop();
                    lblStatus.setText("服务停止");
                    btnAction.setText("启动服务");
                }
            }
        });
        btnAction.setBounds(339, 22, 117, 29);
        getContentPane().add(btnAction);

        panelSetting = new JPanel();
        panelSetting.setBackground(new Color(248, 251, 253));
        panelSetting.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u7F51\u7EDC\u53C2\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelSetting.setBounds(19, 55, 506, 119);
        getContentPane().add(panelSetting);
        panelSetting.setLayout(null);

        JLabel lblip = new JLabel("转发地址(端口可修改)：");
        lblip.setBounds(18, 64, 65, 16);
        panelSetting.add(lblip);

        txtHost = new JTextField();
        txtHost.setText("http://127.0.0.1:8080");
        txtHost.setBounds(82, 59, 331, 26);
        panelSetting.add(txtHost);
        txtHost.setColumns(10);

        labelOne = new JLabel("绑定域名：");
        labelOne.setBounds(18, 30, 65, 16);
        panelSetting.add(labelOne);

        txtDomain = new JTextField();
        txtDomain.setColumns(10);
        txtDomain.setBounds(82, 25, 108, 26);
        panelSetting.add(txtDomain);

        lblwezozcom = new JLabel(".seejoke.com");
        lblwezozcom.setBounds(189, 30, 90, 16);
        panelSetting.add(lblwezozcom);

        JLabel jLabel = new JLabel("流出流量：");
        jLabel.setBounds(18, 92, 65, 16);
        panelSetting.add(jLabel);

        lablTraffic = new JLabel("0KB");
        lablTraffic.setBounds(82, 92, 96, 16);
        panelSetting.add(lablTraffic);

        labelTwo = new JLabel("速度：");
        labelTwo.setBounds(162, 92, 39, 16);
        panelSetting.add(labelTwo);

        lblSpeed = new JLabel("0KB");
        lblSpeed.setBounds(199, 92, 73, 16);
        panelSetting.add(lblSpeed);

        JLabel labelFour = new JLabel("延迟：");
        labelFour.setBounds(305, 92, 39, 16);
        panelSetting.add(labelFour);

        lblPing = new JLabel("0ms");
        lblPing.setBounds(342, 92, 123, 16);
        panelSetting.add(lblPing);

        txtConsole = new JTextArea() {

            /**
             *
             */
            private static final long serialVersionUID = 8749801166570350982L;

            @Override
            public void append(String str) {
                this.setCaretPosition(this.getDocument().getLength());
                str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " - " + str;
                if (this.getText().length() > 100000) {
                    this.setText("");
                }
                super.append(str);
            }
        };
        txtConsole.setText("官方网站:https://seejoke.com\n");
        txtConsole.setBounds(19, 181, 437, 129);
        panelConsole = new JScrollPane(txtConsole, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelConsole.setLocation(20, 210);
        panelConsole.setSize(431, 150);

        getContentPane().add(panelConsole);
        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                change();
                super.componentResized(e);
            }
        });
        change();
        //打开网站
        openBrowser();
    }

    private JLabel lblPing;

    private void openBrowser() {
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                //创建一个URI实例,注意不是URL
                java.net.URI uri = java.net.URI.create("https://seejoke.com/tools");
                //获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                //判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    //获取系统默认浏览器打开链接
                    dp.browse(uri);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化大小
     *
     * @param traffic
     * @return
     */
    private String formatNumber(long traffic) {
        String text = null;
        DecimalFormat format = new DecimalFormat("#.##");
        Double value = traffic * 0.0001221d;
        if (value <= 1024) {
            text = format.format(value) + "KB";
        } else if (value / 1024 > 1) {
            text = format.format((value / 1024)) + "MB";
        } else if (value / 1024 / 1024 > 1) {
            text = format.format((value / 1024 / 1024)) + "GB";
        } else if (value / 1024 / 1024 / 1024 > 1) {
            text = format.format((value / 1024 / 1024 / 1024)) + "TB";
        }
        return text;
    }

    private void change() {
        int width = Application.this.getWidth();
        int height = Application.this.getHeight();
        int wv = width - 40;
        panelSetting.setSize(wv, panelSetting.getHeight());
        panelConsole.setSize(wv, height - panelConsole.getY() - 40);
        btnAction.setLocation(width - 20 - btnAction.getWidth(), btnAction.getY());
    }

    public static void main(String[] args) {
        new Application().setVisible(true);
    }
}