package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import entity.ChiTietHopDong;
import entity.ChiTietThanhToan;
import entity.HopDong;
import entity.KhachHang;
import entity.NhanVienHanhChanh;
import entity.Xe;
import facade.HopDongFacade;
import facade.KhachHangFacade;
import facade.XeFacade;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;

public class PanelDanhSachHopDong extends JPanel {

	private Image img_SearchLon = new ImageIcon(FrmLogin.class.getResource("../image/search.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_RefreshLon = new ImageIcon(FrmLogin.class.getResource("../image/refresh.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_Delete = new ImageIcon(FrmLogin.class.getResource("../image/delete.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_Edit = new ImageIcon(FrmLogin.class.getResource("../image/edit.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_Add = new ImageIcon(PanelQuanLyNhanVien.class.getResource("../image/add.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);

	private JTextField txtTienThua;
	private JTextField txtTongTienHD;
	private JTextField txtMaHoaDon;
	private JTextField txtNhanVienTao;
	private JTextField txtTenXe;
	private JTable tableHD;
	private JTable tableCTHD;
	DefaultTableModel modelHD;
	DefaultTableModel modelCTHD;
	private JTextField txtSoLuong;
	private JTextField txtGiaBan;
	private JTextField txtThanhTienCTHD;
	private JTextField txtTimHoaDon;
	private JTextField txtSoNamBaoHanh;
	private JTextField txtNgayLapHoaDon;
	private JTextField txtNguoiTra;
	private JTextField txtLanTra;
	private JTextField txtTienDaTra;

	private JTable tableChiTietThanhToan;
	DefaultTableModel modelCTTT;

	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private Locale locale = new Locale("en", "EN");
	private String pattern = "###,###.##";
	private DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
	private JTextField txtTienPhaiTra;
	
	private HopDong hopDong = new HopDong();
	private List<ChiTietHopDong> dsCTHD = new ArrayList<ChiTietHopDong>();
	private List<ChiTietThanhToan> dsCTTT = new ArrayList<ChiTietThanhToan>();
	
	private HopDongFacade hopDongFacade;
	private XeFacade xeFacade;
	private JComboBox<String> cbMaXe;
	private JTextField txtTinhTrang;
	private JComboBox<String> cbMaKH;
	private KhachHangFacade khachHangFacade;
	private String maNVDangNhap;
	private JLabel lblMessHD;
	private JLabel lblMessCTHD;
	private JLabel lblMessCTTT;
	
	/**
	 * Create the panel.
	 */
	public PanelDanhSachHopDong(HopDongFacade hopDongFacade, XeFacade xeFacade
			 , KhachHangFacade khachHangFacade, String maNVDangNhap) {

		this.khachHangFacade = khachHangFacade;
		this.hopDongFacade = hopDongFacade;
		this.xeFacade = xeFacade;
		this.maNVDangNhap = maNVDangNhap;
		
		setBackground(new Color(224, 255, 255));
		setForeground(new Color(255, 255, 0));
		setName("");
		setBounds(0, 0, 1086, 676);
		setLayout(null);

		JPanel pnlDanhSachSanPham = new JPanel();
		pnlDanhSachSanPham.setLayout(null);
		pnlDanhSachSanPham.setBorder(new LineBorder(new Color(0, 0, 255)));
		pnlDanhSachSanPham.setBounds(0, 0, 603, 676);
		add(pnlDanhSachSanPham);

		JLabel lblDanhSachHoaDon = new JLabel("DANH S??CH H???P ?????NG");
		lblDanhSachHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachHoaDon.setForeground(new Color(70, 130, 180));
		lblDanhSachHoaDon.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDanhSachHoaDon.setBounds(0, 0, 603, 38);
		pnlDanhSachSanPham.add(lblDanhSachHoaDon);

		JLabel lblNgayToaHoaDon = new JLabel("Ng??y t???o h???p ?????ng:");
		lblNgayToaHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayToaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgayToaHoaDon.setBounds(0, 70, 152, 22);
		pnlDanhSachSanPham.add(lblNgayToaHoaDon);

		JLabel lblMaHoaDon = new JLabel("M?? h???p ?????ng:");
		lblMaHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaHoaDon.setBounds(18, 38, 119, 22);
		pnlDanhSachSanPham.add(lblMaHoaDon);

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setForeground(new Color(0, 0, 255));
		txtMaHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaHoaDon.setEditable(false);
		txtMaHoaDon.setColumns(10);
		txtMaHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtMaHoaDon.setBounds(139, 38, 210, 24);
		pnlDanhSachSanPham.add(txtMaHoaDon);

		JLabel lblMaKhachHang = new JLabel("M?? kh??ch h??ng:");
		lblMaKhachHang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaKhachHang.setBounds(18, 106, 119, 22);
		pnlDanhSachSanPham.add(lblMaKhachHang);

		JLabel lblNNhanVienTao = new JLabel("M?? nh??n vi??n:");
		lblNNhanVienTao.setBounds(10, 141, 127, 22);
		pnlDanhSachSanPham.add(lblNNhanVienTao);
		lblNNhanVienTao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNNhanVienTao.setFont(new Font("Tahoma", Font.PLAIN, 17));

		txtNhanVienTao = new JTextField();
		txtNhanVienTao.setForeground(new Color(0, 0, 255));
		txtNhanVienTao.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtNhanVienTao.setEditable(false);
		txtNhanVienTao.setBounds(139, 141, 210, 24);
		pnlDanhSachSanPham.add(txtNhanVienTao);
		txtNhanVienTao.setColumns(10);
		txtNhanVienTao.setBorder(new LineBorder(new Color(0, 0, 0)));

		JScrollPane scrollPaneHD = new JScrollPane();
		scrollPaneHD.setBounds(0, 392, 603, 280);
		pnlDanhSachSanPham.add(scrollPaneHD);
		String[] headers = { "M?? h???p ?????ng", "Ng??y l???p", "M?? nh??n vi??n", "M?? kh??ch h??ng",
				"B???o h??nh", "T???ng th??nh ti???n", "Ti???n ph???i tr???", "Ti???n th???a", "T??nh tr???ng"};
		modelHD = new DefaultTableModel(headers, 0);
		tableHD = new JTable(modelHD);
		tableHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableHD.getSelectedRow();
				if (row >= 0) {
					String maHD = (String) tableHD.getValueAt(row, 0);
					try {
						HopDong hd = hopDongFacade.timHopDongTheoMaHopDong(maHD);
						if (hd != null) {
							hopDong = hd;
							txtMaHoaDon.setText(maHD);
							txtNgayLapHoaDon.setText(formatter.format(hd.getNgayHopDong()));
							cbMaKH.setSelectedItem(hd.getKhDaiDien().getMaKH());
							txtNhanVienTao.setText(hd.getNhanVienLapHD().getMaNV());
							txtSoNamBaoHanh.setText(hd.getThoiGianBH()+"");
							txtTienThua.setText(dcf.format(hd.getTienThua()));
							txtTienPhaiTra.setText(dcf.format(hd.getTienPhaiTra()));
							txtTongTienHD.setText(dcf.format(hd.getTongThanhTien()));
							clearCTHD();
							clearCTTT();
							if (hd.isTinhTrangThanhToan()) {
								txtTinhTrang.setText("Ho??n t???t");
							} else{
								txtTinhTrang.setText("Ch??a ho??n t???t");
							}
							dsCTHD = hd.getDsCTHD();
							dsCTTT = hd.getDsCTTT();
							txtLanTra.setText(setSoLanThanhToanTuDong()+"");
							updateTableCTHD(hd.getDsCTHD());
							updateTableCTTT(hd.getDsCTTT());
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		scrollPaneHD.setViewportView(tableHD);

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setLayout(null);
		pnTimKiem.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnTimKiem.setBounds(0, 348, 603, 43);
		pnlDanhSachSanPham.add(pnTimKiem);

		JButton btnTimKiemHD = new JButton("");
		btnTimKiemHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keywords = txtTimHoaDon.getText().trim();
				try {
					HopDong hd = hopDongFacade.timHopDongTheoMaHopDong(keywords);
					if (hd != null) {
						xoaHetTableHopDong();
						String tinhTrang = "Ch??a ho??n t???t";
						if (hd.isTinhTrangThanhToan()) {
							tinhTrang = "Ho??n t???t";
						}
						String[] rowData = {hd.getSoHopDong(), formatter.format(hd.getNgayHopDong())
								, hd.getNhanVienLapHD().getMaNV(), hd.getKhDaiDien().getMaKH()
								, hd.getThoiGianBH()+"", dcf.format(hd.getTongThanhTien())
								, dcf.format(hd.getTienPhaiTra()), dcf.format(hd.getTienThua())
								, tinhTrang};
						modelHD.addRow(rowData);
					} else {
						List<HopDong> dsHD = hopDongFacade.timHopDongTheoTextSearch(keywords);
						xoaHetTableHopDong();
						if (dsHD.isEmpty()) {
							String[] rowData = {"","","","","","","","",""};
							modelHD.addRow(rowData);
						}
						for (HopDong hdTK : dsHD) {
							String tinhTrang = "Ch??a ho??n t???t";
							if (hdTK.isTinhTrangThanhToan()) {
								tinhTrang = "Ho??n t???t";
							}
							String[] rowData = {hdTK.getSoHopDong(), formatter.format(hdTK.getNgayHopDong())
									, hdTK.getNhanVienLapHD().getMaNV(), hdTK.getKhDaiDien().getMaKH()
									, hdTK.getThoiGianBH()+"", dcf.format(hdTK.getTongThanhTien())
									, dcf.format(hdTK.getTienPhaiTra()), dcf.format(hdTK.getTienThua())
									, tinhTrang};
							modelHD.addRow(rowData);
						}
					}
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTimKiemHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTimKiemHD.setBounds(520, 2, 41, 36);
		btnTimKiemHD.setIcon(new ImageIcon(img_SearchLon));
		pnTimKiem.add(btnTimKiemHD);

		txtTimHoaDon = new JTextField();
		txtTimHoaDon.setForeground(new Color(128, 128, 128));
		txtTimHoaDon.setSelectionColor(new Color(0, 0, 0));
		txtTimHoaDon.setText("T??m theo m?? h???p ?????ng, m?? nh??n vi??n, m?? kh??ch h??ng");
		txtTimHoaDon.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimHoaDon.getText().equals("T??m theo m?? h???p ?????ng, m?? nh??n vi??n, m?? kh??ch h??ng")) {
					txtTimHoaDon.setText("");
				} else {
					txtTimHoaDon.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtTimHoaDon.getText().equals("")) {
					txtTimHoaDon.setText("T??m theo m?? h???p ?????ng, m?? nh??n vi??n, m?? kh??ch h??ng");
				}
			}
		});
		txtTimHoaDon.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		txtTimHoaDon.setColumns(10);
		txtTimHoaDon.setBounds(8, 4, 511, 30);
		pnTimKiem.add(txtTimHoaDon);

		JButton btnLamMoiHD = new JButton("");
		btnLamMoiHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearHopDong();
				updateComboBox();
			}
		});
		btnLamMoiHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLamMoiHD.setBounds(560, 2, 41, 36);
		btnLamMoiHD.setIcon(new ImageIcon(img_RefreshLon));
		pnTimKiem.add(btnLamMoiHD);

		JPanel pnChucNang = new JPanel();
		pnChucNang.setBorder(new LineBorder(new Color(30, 144, 255), 2));
		pnChucNang.setBounds(359, 59, 239, 71);
		pnlDanhSachSanPham.add(pnChucNang);
		pnChucNang.setLayout(null);

		JButton btnXoa = new JButton("Xo?? h???p ?????ng");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hoiNhac = JOptionPane.showConfirmDialog((Component) e.getSource(),"C?? ch???n ch???n x??a","Ch?? ??",JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					String maHD = txtMaHoaDon.getText().trim();
					try {
							if (hopDongFacade.xoaHopDong(maHD)) {
								lblMessHD.setText("X??a th??nh c??ng");
								clearHopDong();
							}
							
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnXoa.setBounds(2, 2, 234, 33);
		pnChucNang.add(btnXoa);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setIcon(new ImageIcon(img_Delete));

		JButton btnCapNhat = new JButton("C???p nh???t h???p ?????ng");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maKH = (String) cbMaKH.getSelectedItem();
				int soNamBH = Integer.parseInt(txtSoNamBaoHanh.getText().trim());
				hopDong.setThoiGianBH(soNamBH);
				hopDong.setKhDaiDien(new KhachHang(maKH));
				try {
					if (hopDongFacade.capNhatHopDong(hopDong)) {
						lblMessHD.setText("C???p nh???t th??nh c??ng");
						updateTableHopDong();
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnCapNhat.setBounds(2, 35, 234, 33);
		pnChucNang.add(btnCapNhat);
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCapNhat.setIcon(new ImageIcon(img_Edit));

		JLabel lblSoNgayHopDong = new JLabel("S??? n??m b???o h??nh:");
		lblSoNgayHopDong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoNgayHopDong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSoNgayHopDong.setBounds(0, 174, 137, 22);
		pnlDanhSachSanPham.add(lblSoNgayHopDong);

		txtSoNamBaoHanh = new JTextField();
		txtSoNamBaoHanh.setForeground(Color.BLACK);
		txtSoNamBaoHanh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoNamBaoHanh.setColumns(10);
		txtSoNamBaoHanh.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtSoNamBaoHanh.setBounds(139, 175, 210, 24);
		pnlDanhSachSanPham.add(txtSoNamBaoHanh);

		txtNgayLapHoaDon = new JTextField();
		txtNgayLapHoaDon.setEditable(false);
		txtNgayLapHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtNgayLapHoaDon.setBounds(159, 72, 190, 24);
		pnlDanhSachSanPham.add(txtNgayLapHoaDon);
		txtNgayLapHoaDon.setColumns(10);

		JLabel lblTongTien = new JLabel("T???ng th??nh ti???n:");
		lblTongTien.setBounds(10, 282, 168, 22);
		pnlDanhSachSanPham.add(lblTongTien);
		lblTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 17));

		JLabel lblTienHang = new JLabel("Ti???n th???a:");
		lblTienHang.setBounds(68, 253, 110, 22);
		pnlDanhSachSanPham.add(lblTienHang);
		lblTienHang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTienHang.setFont(new Font("Tahoma", Font.BOLD, 17));

		txtTienThua = new JTextField();
		txtTienThua.setBounds(188, 253, 210, 24);
		pnlDanhSachSanPham.add(txtTienThua);
		txtTienThua.setForeground(new Color(255, 0, 0));
		txtTienThua.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtTienThua.setEditable(false);
		txtTienThua.setColumns(10);
		txtTienThua.setBorder(new LineBorder(new Color(0, 0, 0)));

		txtTongTienHD = new JTextField();
		txtTongTienHD.setBounds(188, 283, 210, 24);
		pnlDanhSachSanPham.add(txtTongTienHD);
		txtTongTienHD.setForeground(new Color(255, 0, 0));
		txtTongTienHD.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtTongTienHD.setEditable(false);
		txtTongTienHD.setColumns(10);
		txtTongTienHD.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblTongTienPhaiTra = new JLabel("T???ng ti???n ph???i tr???:");
		lblTongTienPhaiTra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTienPhaiTra.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTongTienPhaiTra.setBounds(10, 312, 168, 22);
		pnlDanhSachSanPham.add(lblTongTienPhaiTra);
		
		txtTienPhaiTra = new JTextField();
		txtTienPhaiTra.setForeground(Color.RED);
		txtTienPhaiTra.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtTienPhaiTra.setEditable(false);
		txtTienPhaiTra.setColumns(10);
		txtTienPhaiTra.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTienPhaiTra.setBounds(188, 313, 210, 24);
		pnlDanhSachSanPham.add(txtTienPhaiTra);
		
		JLabel lblTinhTrang = new JLabel("T??nh tr???ng:");
		lblTinhTrang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTinhTrang.setBounds(0, 207, 137, 22);
		pnlDanhSachSanPham.add(lblTinhTrang);
		
		txtTinhTrang = new JTextField();
		txtTinhTrang.setForeground(Color.BLACK);
		txtTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTinhTrang.setEditable(false);
		txtTinhTrang.setColumns(10);
		txtTinhTrang.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTinhTrang.setBounds(139, 208, 210, 24);
		pnlDanhSachSanPham.add(txtTinhTrang);
		
		cbMaKH = new JComboBox<String>();
		cbMaKH.setFont(new Font("Times New Roman", Font.BOLD, 18));
		cbMaKH.setBounds(139, 106, 210, 24);
		pnlDanhSachSanPham.add(cbMaKH);
		
		lblMessHD = new JLabel("");
		lblMessHD.setForeground(Color.RED);
		lblMessHD.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblMessHD.setBounds(359, 161, 234, 24);
		pnlDanhSachSanPham.add(lblMessHD);

		JPanel panelHoaDon = new JPanel();
		panelHoaDon.setLayout(null);
		panelHoaDon.setBorder(new LineBorder(new Color(255, 0, 0)));
		panelHoaDon.setBounds(607, 0, 479, 372);
		add(panelHoaDon);

		JLabel lblDanhSachChiTietHoaDon = new JLabel("CHI TI???T H???P ?????NG");
		lblDanhSachChiTietHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachChiTietHoaDon.setForeground(new Color(220, 20, 60));
		lblDanhSachChiTietHoaDon.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDanhSachChiTietHoaDon.setBounds(0, 0, 479, 38);
		panelHoaDon.add(lblDanhSachChiTietHoaDon);

		txtTenXe = new JTextField();
		txtTenXe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenXe.setEditable(false);
		txtTenXe.setColumns(10);
		txtTenXe.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTenXe.setBounds(115, 70, 210, 24);
		panelHoaDon.add(txtTenXe);

		JScrollPane scrollPaneCTHD = new JScrollPane();
		scrollPaneCTHD.setBounds(0, 236, 479, 133);
		panelHoaDon.add(scrollPaneCTHD);

		String[] headers1 = { "M?? xe", "T??n xe", "H??ng xe", "S??? l?????ng", "????n gi??",
		"Th??nh ti???n" };
		modelCTHD = new DefaultTableModel(headers1, 0);
		tableCTHD = new JTable(modelCTHD);
		tableCTHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableCTHD.getSelectedRow();
				String maXe = (String) tableCTHD.getValueAt(row, 0);
				for (ChiTietHopDong ct : dsCTHD) {
					if (ct.getXe().getMaXe().equals(maXe)) {
						cbMaXe.setSelectedItem(maXe);
						txtSoLuong.setText(ct.getSoLuong()+"");
						txtGiaBan.setText(dcf.format(ct.getDonGia()));
						txtThanhTienCTHD.setText(dcf.format(ct.getThanhTien()));
					}
				}
				
			}
		});
		scrollPaneCTHD.setViewportView(tableCTHD);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtSoLuong.setBounds(115, 105, 210, 24);
		panelHoaDon.add(txtSoLuong);

		txtGiaBan = new JTextField();
		txtGiaBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtGiaBan.setEditable(false);
		txtGiaBan.setColumns(10);
		txtGiaBan.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGiaBan.setBounds(115, 139, 210, 24);
		panelHoaDon.add(txtGiaBan);

		txtThanhTienCTHD = new JTextField();
		txtThanhTienCTHD.setBounds(115, 173, 210, 24);
		panelHoaDon.add(txtThanhTienCTHD);
		txtThanhTienCTHD.setForeground(new Color(255, 0, 0));
		txtThanhTienCTHD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtThanhTienCTHD.setEditable(false);
		txtThanhTienCTHD.setColumns(10);
		txtThanhTienCTHD.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblThanhTien = new JLabel("Th??nh ti???n:");
		lblThanhTien.setBounds(0, 168, 111, 31);
		panelHoaDon.add(lblThanhTien);
		lblThanhTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThanhTien.setFont(new Font("Tahoma", Font.BOLD, 17));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(30, 144, 255), 2));
		panel.setBounds(335, 39, 134, 154);
		panelHoaDon.add(panel);
		panel.setLayout(null);

		JButton btnXoaSP = new JButton("Xo?? Xe");
		btnXoaSP.setHorizontalAlignment(SwingConstants.LEFT);
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableCTHD.getSelectedColumn();
				if (row >= 0) {
					try {
						String maXe = cbMaXe.getSelectedItem()+"";
						for (int i = 0; i < dsCTHD.size(); i++) {
							if (dsCTHD.get(i).getXe().getMaXe().equals(maXe)) {
								dsCTHD.remove(i);
								updateTableCTHD(dsCTHD);
								capNhatChiTietHopDongTrongHopDong();
								if (hopDongFacade.capNhatHopDong(hopDong)) {
									updateTableHopDong();
									clearCTHD();
									lblMessCTHD.setText("X??a th??nh c??ng");
								}
							}
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnXoaSP.setBounds(2, 40, 130, 38);
		btnXoaSP.setIcon(new ImageIcon(img_Delete));
		panel.add(btnXoaSP);
		btnXoaSP.setForeground(new Color(0, 0, 0));
		btnXoaSP.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnSuaChiTietHD = new JButton("C???p nh???t");
		btnSuaChiTietHD.setHorizontalAlignment(SwingConstants.LEFT);
		btnSuaChiTietHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!hopDong.getSoHopDong().equals("")) {
					if (validCTHD()) {
						String maXe = (String) cbMaXe.getSelectedItem();
						int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
						double donGia;
						try {
							donGia = Double.parseDouble(dcf.parse(txtGiaBan.getText()).toString()) ;
							
							for (int i = 0; i < dsCTHD.size(); i++) {
								if (dsCTHD.get(i).getXe().getMaXe().equals(maXe)) {
									ChiTietHopDong chiTiet = dsCTHD.get(i);
									chiTiet.setDonGia(donGia);
									chiTiet.setSoLuong(soLuong);
									chiTiet.setXe(new Xe(maXe));
									dsCTHD.set(i, chiTiet);
									updateTableCTHD(dsCTHD);
									txtThanhTienCTHD.setText(dcf.format(chiTiet.getThanhTien()));
									capNhatChiTietHopDongTrongHopDong();
									if (hopDongFacade.capNhatHopDong(hopDong)) {
										lblMessCTHD.setText("C???p nh???t th??nh c??ng");
										updateTableHopDong();
									}
									break;
								}
							}
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnSuaChiTietHD.setBounds(2, 77, 130, 38);
		btnSuaChiTietHD.setIcon(new ImageIcon(img_Edit));
		panel.add(btnSuaChiTietHD);
		btnSuaChiTietHD.setForeground(new Color(0, 0, 0));
		btnSuaChiTietHD.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnThemXe = new JButton("Th??m Xe");
		btnThemXe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!hopDong.getSoHopDong().equals("")) {
					if (validCTHD()) {
						String maXe = (String) cbMaXe.getSelectedItem();
						int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
						double donGia;
						try {
							donGia = Double.parseDouble(dcf.parse(txtGiaBan.getText()).toString()) ;
							boolean chuaCo = true;
							for (ChiTietHopDong chiTiet : dsCTHD) {
								if (chiTiet.getXe().getMaXe().equals(maXe)) {
									lblMessCTHD.setText("Xe ???? c?? trong h???p ?????ng");
									chuaCo = false;
								}
							}
							if (chuaCo) {
								ChiTietHopDong ct = new ChiTietHopDong(soLuong, donGia);
								ct.setXe(new Xe(maXe));
								dsCTHD.add(ct);
								updateTableCTHD(dsCTHD);
								hopDong.themChiTietHopDong(soLuong, donGia, new Xe(maXe));
								
								if (hopDongFacade.capNhatHopDong(hopDong)) {
									lblMessCTHD.setText("Th??m th??nh c??ng");
									updateTableHopDong();
									txtThanhTienCTHD.setText(dcf.format(ct.getThanhTien()));
								}
							}
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnThemXe.setHorizontalAlignment(SwingConstants.LEFT);
		btnThemXe.setForeground(Color.BLACK);
		btnThemXe.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThemXe.setBounds(2, 2, 130, 38);
		btnThemXe.setIcon(new ImageIcon(img_Add));
		panel.add(btnThemXe);
		
		JButton btnLamMoiCTHD = new JButton("L??m m???i");
		btnLamMoiCTHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCTHD();
			}
		});
		btnLamMoiCTHD.setIcon(new ImageIcon(img_RefreshLon));
		btnLamMoiCTHD.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoiCTHD.setForeground(Color.BLACK);
		btnLamMoiCTHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoiCTHD.setBounds(2, 114, 130, 38);
		panel.add(btnLamMoiCTHD);

		JLabel lblMaXe = new JLabel("M?? xe:");
		lblMaXe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaXe.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaXe.setBounds(54, 39, 57, 22);
		panelHoaDon.add(lblMaXe);

		JLabel lblTenXe = new JLabel("T??n Xe:");
		lblTenXe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenXe.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTenXe.setBounds(44, 74, 67, 22);
		panelHoaDon.add(lblTenXe);

		JLabel lblDonGia = new JLabel("????n gi??:");
		lblDonGia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDonGia.setBounds(38, 140, 73, 22);
		panelHoaDon.add(lblDonGia);

		JLabel lblSoLuong = new JLabel("S??? l?????ng:");
		lblSoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSoLuong.setBounds(41, 106, 70, 22);
		panelHoaDon.add(lblSoLuong);

		cbMaXe = new JComboBox<String>();
		cbMaXe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maXe = (String) cbMaXe.getSelectedItem();
				try {
					Xe xe = xeFacade.timXeTheoMa(maXe);
					txtTenXe.setText(xe.getTenXe());
					txtGiaBan.setText(dcf.format(xe.getDonGia()));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		cbMaXe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		cbMaXe.setBounds(115, 37, 210, 24);
		panelHoaDon.add(cbMaXe);
		
		lblMessCTHD = new JLabel("");
		lblMessCTHD.setForeground(Color.RED);
		lblMessCTHD.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblMessCTHD.setBounds(38, 208, 345, 24);
		panelHoaDon.add(lblMessCTHD);

		JPanel panelChiTietTT = new JPanel();
		panelChiTietTT.setLayout(null);
		panelChiTietTT.setBorder(new LineBorder(new Color(255, 0, 0)));
		panelChiTietTT.setBounds(607, 376, 479, 300);
		add(panelChiTietTT);

		JLabel lblDanhSachChiTietThanhToan = new JLabel("CHI TI???T THANH TO??N");
		lblDanhSachChiTietThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachChiTietThanhToan.setForeground(new Color(100, 149, 237));
		lblDanhSachChiTietThanhToan.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDanhSachChiTietThanhToan.setBounds(0, 0, 479, 38);
		panelChiTietTT.add(lblDanhSachChiTietThanhToan);

		txtNguoiTra = new JTextField();
		txtNguoiTra.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtNguoiTra.setColumns(10);
		txtNguoiTra.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtNguoiTra.setBounds(90, 81, 210, 24);
		panelChiTietTT.add(txtNguoiTra);

		txtLanTra = new JTextField();
		txtLanTra.setEditable(false);
		txtLanTra.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtLanTra.setColumns(10);
		txtLanTra.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtLanTra.setBounds(90, 49, 210, 24);
		panelChiTietTT.add(txtLanTra);

		JScrollPane scrollPaneCTTT = new JScrollPane();
		scrollPaneCTTT.setBounds(0, 182, 479, 118);
		panelChiTietTT.add(scrollPaneCTTT);

		String[] header2= {"L???n tr???","Ng?????i tr???","Ti???n ???? tr???"};
		modelCTTT = new DefaultTableModel(header2, 0);
		tableChiTietThanhToan = new JTable(modelCTTT);
		tableChiTietThanhToan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableChiTietThanhToan.getSelectedRow();
				String lanTra = (String) tableChiTietThanhToan.getValueAt(row, 0);
				String tenNguoiTra = (String) tableChiTietThanhToan.getValueAt(row, 1);
				String tienTra = (String) tableChiTietThanhToan.getValueAt(row, 2);
				txtLanTra.setText(lanTra);
				txtNguoiTra.setText(tenNguoiTra);
				txtTienDaTra.setText(tienTra);
			}
		});
		scrollPaneCTTT.setViewportView(tableChiTietThanhToan);

		txtTienDaTra = new JTextField();
		txtTienDaTra.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTienDaTra.setColumns(10);
		txtTienDaTra.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTienDaTra.setBounds(90, 116, 210, 24);
		panelChiTietTT.add(txtTienDaTra);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(30, 144, 255), 2));
		panel_1.setBounds(335, 38, 134, 118);
		panelChiTietTT.add(panel_1);

		JButton btnThemCT = new JButton("Th??m CT");
		btnThemCT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!hopDong.getSoHopDong().equals("")) {
					if (validCTTT()) {
						int lanTra = Integer.parseInt(txtLanTra.getText().trim());
						String tenNguoiTra = txtNguoiTra.getText().trim();
						double tienTra;
						try {
							tienTra = Double.parseDouble(dcf.parse(txtTienDaTra.getText()).toString()) ;
							ChiTietThanhToan ct = new ChiTietThanhToan(lanTra, tienTra, tenNguoiTra);
							dsCTTT.add(ct);
							updateTableCTTT(dsCTTT);
							hopDong.themChiTietThanhToan(lanTra, tienTra, tenNguoiTra);
							
							if (hopDongFacade.capNhatHopDong(hopDong)) {
								lblMessCTTT.setText("Th??m th??nh c??ng");
								updateTableHopDong();
								clearCTTT();
							}
						} catch (ParseException e2) {
							e2.printStackTrace();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnThemCT.setForeground(Color.BLACK);
		btnThemCT.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThemCT.setBounds(2, 2, 130, 38);
		btnThemCT.setIcon(new ImageIcon(img_Add));
		panel_1.add(btnThemCT);

		JButton btnCapNhatCT = new JButton("C???p nh???t");
		btnCapNhatCT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!hopDong.getSoHopDong().equals("")) {
					if (validCTTT()) {
						int lanTra = Integer.parseInt(txtLanTra.getText().trim());
						String tenNguoiTra = txtNguoiTra.getText().trim();
						double tienTra;
						try {
							tienTra = Double.parseDouble(dcf.parse(txtTienDaTra.getText()).toString()) ;
							
							for (int i = 0; i < dsCTTT.size(); i++) {
								if (dsCTTT.get(i).getLanTra() == lanTra) {
									ChiTietThanhToan ct = dsCTTT.get(i);
									ct.setLanTra(lanTra);
									ct.setTenNguoiTra(tenNguoiTra);
									ct.setTienTra(tienTra);
									dsCTTT.set(i, ct);
									capNhatChiTietThanhToanTrongHopDong();
									updateTableCTTT(dsCTTT);
									if (hopDongFacade.capNhatHopDong(hopDong)) {
										lblMessCTTT.setText("C???p nh???t th??nh c??ng");
										updateTableHopDong();
									}
									break;
								}
							}
						} catch (ParseException e2) {
							e2.printStackTrace();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnCapNhatCT.setForeground(Color.BLACK);
		btnCapNhatCT.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCapNhatCT.setBounds(2, 40, 130, 38);
		btnCapNhatCT.setIcon(new ImageIcon(img_Edit));
		panel_1.add(btnCapNhatCT);
		
		JButton btnLamMoiCTTT = new JButton("L??m m???i");
		btnLamMoiCTTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCTTT();
				txtLanTra.setText(setSoLanThanhToanTuDong()+"");
			}
		});
		btnLamMoiCTTT.setIcon(new ImageIcon(img_RefreshLon));
		btnLamMoiCTTT.setForeground(Color.BLACK);
		btnLamMoiCTTT.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoiCTTT.setBounds(2, 78, 130, 38);
		panel_1.add(btnLamMoiCTTT);

		JLabel lblLanTra = new JLabel("L???n tr???:");
		lblLanTra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanTra.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLanTra.setBounds(18, 51, 70, 22);
		panelChiTietTT.add(lblLanTra);

		JLabel lblNguoiTra = new JLabel("Ng?????i tr???:");
		lblNguoiTra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNguoiTra.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNguoiTra.setBounds(13, 85, 75, 22);
		panelChiTietTT.add(lblNguoiTra);

		JLabel lblTienTra = new JLabel("Ti???n ???? tr???:");
		lblTienTra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTienTra.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTienTra.setBounds(0, 117, 88, 22);
		panelChiTietTT.add(lblTienTra);
		
		lblMessCTTT = new JLabel("");
		lblMessCTTT.setForeground(Color.RED);
		lblMessCTTT.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblMessCTTT.setBounds(18, 150, 312, 24);
		panelChiTietTT.add(lblMessCTTT);

		setVisible(true);
		updateTableHopDong();
		updateComboBox();
	}
	
	public boolean validHopDong() {
		try {
			int soNamBH = Integer.parseInt(txtSoNamBaoHanh.getText().trim());
			if (soNamBH < 0) {
				lblMessHD.setText("N??m b???o h??nh ph???i >= 0");
				txtSoNamBaoHanh.requestFocus();
				return false;
			}
		} catch (Exception e) {
			lblMessHD.setText("Sai ?????nh d???ng n??m b???o h??nh");
			txtSoNamBaoHanh.requestFocus();
			return false;
		}
		return true;
	}
	
	public boolean validCTHD() {
		try {
			int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
			if (soLuong <= 0) {
				lblMessCTHD.setText("S??? l?????ng ph???i > 0");
				txtSoLuong.requestFocus();
				return false;
			}
		} catch (Exception e) {
			lblMessCTHD.setText("Sai ?????nh d???ng s??? l?????ng");
			txtSoLuong.requestFocus();
			return false;
		}
		return true;
	}
	
	public boolean validCTTT() {
		try {
			double tienTra = Double.parseDouble(dcf.parse(txtTienDaTra.getText()).toString());
			if (tienTra <= 0) {
				lblMessCTTT.setText("Ti???n tr??? ph???i > 0");
				txtTienDaTra.requestFocus();
				return false;
			}
		} catch (Exception e) {
			lblMessCTTT.setText("Sai ?????nh d???ng ti???n tr???");
			txtTienDaTra.requestFocus();
			return false;
		}
		return true;
	}
	
	public void xoaHetTableHopDong() {
		DefaultTableModel dm = (DefaultTableModel) tableHD.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	public void xoaHetTableCTHD() {
		DefaultTableModel dm = (DefaultTableModel) tableCTHD.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	public void xoaHetTableCTTT() {
		DefaultTableModel dm = (DefaultTableModel) tableChiTietThanhToan.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	public void updateTableCTHD(List<ChiTietHopDong> list) {
		xoaHetTableCTHD();
		if (list.isEmpty()) {
			String[] row = {"","","", "", ""};
			modelCTHD.addRow(row);
		} else {
			try {
				for (ChiTietHopDong ct : list) {
					Xe xe = xeFacade.timXeTheoMa(ct.getXe().getMaXe());
					String[] rows = {ct.getXe().getMaXe(), xe.getTenXe(),xe.getHangXe(),
							ct.getSoLuong()+"", dcf.format(ct.getDonGia()), dcf.format(ct.getThanhTien())};
					modelCTHD.addRow(rows);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		tableCTHD.setModel(modelCTHD);
		
	}

	public void updateTableCTTT(List<ChiTietThanhToan> list) {
		xoaHetTableCTTT();
		if (list.isEmpty()) {
			String[] row = {"","",""};
			modelCTTT.addRow(row);
		} else {
			for (ChiTietThanhToan ct : list) {
				String[] row = {ct.getLanTra()+"",ct.getTenNguoiTra(),dcf.format(ct.getTienTra())};
				modelCTTT.addRow(row);
			}
		}
		tableChiTietThanhToan.setModel(modelCTTT);
	}
	
	public void updateComboBox() {
		try {
			List<Xe> list = xeFacade.getDSXe();
			for (Xe xe : list) {
				cbMaXe.addItem(xe.getMaXe());
			}
			List<KhachHang> listKH = khachHangFacade.getDSKhachHang();
			for (KhachHang khachHang : listKH) {
				cbMaKH.addItem(khachHang.getMaKH());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public void clearCTHD() {
		cbMaXe.setSelectedIndex(0);
		txtSoLuong.setText("");
		txtGiaBan.setText("");
		txtThanhTienCTHD.setText("");
		lblMessCTHD.setText("");
	}
	
	public void clearCTTT() {
		txtLanTra.setText("");
		txtNguoiTra.setText("");
		txtTienDaTra.setText("");
		lblMessCTTT.setText("");
	}
	
	public void clearHopDong() {
		txtMaHoaDon.setText("");
		txtNgayLapHoaDon.setText("");
		cbMaKH.setSelectedIndex(0);
		txtNhanVienTao.setText(maNVDangNhap);
		txtTinhTrang.setText("");
		txtSoNamBaoHanh.setText("");
		lblMessHD.setText("");
		
		txtTienPhaiTra.setText("");
		txtTienThua.setText("");
		txtTongTienHD.setText("");
		
		updateTableHopDong();
		dsCTHD = new ArrayList<ChiTietHopDong>();
		dsCTTT = new ArrayList<ChiTietThanhToan>();
		
		updateTableCTHD(dsCTHD);
		updateTableCTTT(dsCTTT);
		
		clearCTHD();
		clearCTTT();
	}
	
	public void updateTableHopDong() {
		xoaHetTableHopDong();
		try {
			List<HopDong> listHD = hopDongFacade.getDSHopDong();
			for (HopDong hopDong : listHD) {
				String tinhTrang;
				if (hopDong.isTinhTrangThanhToan()) {
					tinhTrang = "Ho??n t???t";
				} else {
					tinhTrang = "Ch??a ho??n t???t";
				}
				String[] row = {hopDong.getSoHopDong(),formatter.format(hopDong.getNgayHopDong())
						, hopDong.getNhanVienLapHD().getMaNV(), hopDong.getKhDaiDien().getMaKH()
						, hopDong.getThoiGianBH()+"", dcf.format(hopDong.getTongThanhTien())
						, dcf.format(hopDong.getTienPhaiTra()), dcf.format(hopDong.getTienThua())
						, tinhTrang};
				modelHD.addRow(row);
			}
			tableHD.setModel(modelHD);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
	
	public int setSoLanThanhToanTuDong() {
		int max = 0;
		for (ChiTietThanhToan chiTietThanhToan : dsCTTT) {
			if (chiTietThanhToan.getLanTra() > max) {
				max = chiTietThanhToan.getLanTra();
			}
		}
		return max + 1;
	}
	
	public void capNhatChiTietHopDongTrongHopDong() {
		hopDong.xoaHetChiTietHopDong();
		for (ChiTietHopDong chiTietHopDong : dsCTHD) {
			hopDong.themChiTietHopDong(chiTietHopDong.getSoLuong(), chiTietHopDong.getDonGia(), chiTietHopDong.getXe());
		}
	}
	
	public void capNhatChiTietThanhToanTrongHopDong() {
		hopDong.xoaHetChiTietThanhToan();
		for (ChiTietThanhToan chiTietThanhToan : dsCTTT) {
			hopDong.themChiTietThanhToan(chiTietThanhToan.getLanTra(), chiTietThanhToan.getTienTra(), chiTietThanhToan.getTenNguoiTra());
		}
	}
}
