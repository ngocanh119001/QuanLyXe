package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import entity.HopDong;
import entity.NhanVienKyThuat;
import entity.PhieuNhanXet;
import facade.HopDongFacade;
import facade.PhieuNhanXetFacade;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPhieuNhanXet extends JPanel {
	private Image img_Add = new ImageIcon(PanelQuanLyNhanVien.class.getResource("../image/add.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_Delete = new ImageIcon(PanelQuanLyNhanVien.class.getResource("../image/delete.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_Edit = new ImageIcon(PanelQuanLyNhanVien.class.getResource("../image/edit.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_New = new ImageIcon(PanelQuanLyNhanVien.class.getResource("../image/new.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_Search = new ImageIcon(PanelQuanLyNhanVien.class.getResource("../image/search.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
	private Image img_RefreshLon = new ImageIcon(FrmLogin.class.getResource("../image/refresh.png")).getImage()
			.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);

	private JTextField txtMaPhieu;
	private JTextField txtTenLinhKien;
	private JTextField txtLiDo;
	private JTextField txtGia;
	private JTextField txtMaNhanVien;
	private JTextField txtTimKiem;

	private JTable tablePhieuNX;
	DefaultTableModel modelPNX;
	
	private Locale locale = new Locale("en", "EN");
	private String pattern = "###,###.##";
	private DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
	
	private PhieuNhanXetFacade phieuNhanXetFacade;
	private HopDongFacade hopDongFacade;
	private JComboBox<String> cbSoHopDong;
	private String maNVDangNhap;
	private JLabel lblMess;
	private JComboBox<String> cbLoiThuocVe;

	/**
	 * Create the panel.
	 */
	public PanelPhieuNhanXet(PhieuNhanXetFacade phieuNhanXetFacade
			, HopDongFacade hopDongFacade, String maNVDangNhap) {
		this.maNVDangNhap = maNVDangNhap;
		this.phieuNhanXetFacade = phieuNhanXetFacade;
		this.hopDongFacade = hopDongFacade;
		
		dcf.applyPattern(pattern);
		
		setForeground(new Color(255, 255, 0));
		setName("");
		setBounds(1, 0, 1087, 706);
		setLayout(null);

		JLabel lblThngTinPhiu = new JLabel("THÔNG TIN PHIẾU NHẬN XÉT");
		lblThngTinPhiu.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngTinPhiu.setForeground(new Color(165, 42, 42));
		lblThngTinPhiu.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblThngTinPhiu.setBounds(0, 0, 1085, 41);
		add(lblThngTinPhiu);

		JLabel lblMaPhieu = new JLabel("Mã phiếu:");
		lblMaPhieu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaPhieu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaPhieu.setBounds(0, 49, 111, 22);
		add(lblMaPhieu);

		txtMaPhieu = new JTextField();
		txtMaPhieu.setForeground(Color.BLUE);
		txtMaPhieu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaPhieu.setEditable(false);
		txtMaPhieu.setColumns(10);
		txtMaPhieu.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtMaPhieu.setBounds(116, 47, 380, 24);
		add(txtMaPhieu);

		JLabel lblSoHopDong = new JLabel("Số hợp đồng:");
		lblSoHopDong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoHopDong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSoHopDong.setBounds(0, 84, 111, 22);
		add(lblSoHopDong);

		JLabel lblTenLinhKien = new JLabel("Tên linh kiện:");
		lblTenLinhKien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTenLinhKien.setBounds(0, 157, 111, 22);
		add(lblTenLinhKien);

		txtTenLinhKien = new JTextField();
		txtTenLinhKien.setForeground(Color.BLUE);
		txtTenLinhKien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenLinhKien.setColumns(10);
		txtTenLinhKien.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTenLinhKien.setBounds(116, 155, 380, 24);
		add(txtTenLinhKien);

		JLabel lblLiDo = new JLabel("Lí do:");
		lblLiDo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLiDo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLiDo.setBounds(0, 122, 111, 22);
		add(lblLiDo);

		txtLiDo = new JTextField();
		txtLiDo.setForeground(Color.BLUE);
		txtLiDo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtLiDo.setColumns(10);
		txtLiDo.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtLiDo.setBounds(116, 120, 380, 24);
		add(txtLiDo);

		JLabel lblLoiThuocVe = new JLabel("Lỗi thuộc về:");
		lblLoiThuocVe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoiThuocVe.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLoiThuocVe.setBounds(581, 49, 111, 22);
		add(lblLoiThuocVe);

		JLabel lblGia = new JLabel("Giá bảo hành:");
		lblGia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGia.setBounds(581, 87, 111, 22);
		add(lblGia);

		txtGia = new JTextField();
		txtGia.setForeground(Color.BLUE);
		txtGia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtGia.setColumns(10);
		txtGia.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtGia.setBounds(697, 85, 380, 24);
		add(txtGia);

		JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
		lblMaNhanVien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMaNhanVien.setBounds(581, 122, 111, 22);
		add(lblMaNhanVien);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setForeground(Color.BLUE);
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtMaNhanVien.setBounds(697, 120, 380, 24);
		add(txtMaNhanVien);

		cbSoHopDong = new JComboBox();
		cbSoHopDong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbSoHopDong.setBounds(116, 82, 380, 24);
		add(cbSoHopDong);

		cbLoiThuocVe = new JComboBox();
		cbLoiThuocVe.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cbLoiThuocVe.setBounds(697, 47, 380, 24);
		add(cbLoiThuocVe);

		JScrollPane scrollPanePNX = new JScrollPane();
		scrollPanePNX.setBounds(0, 347, 1086, 359);
		add(scrollPanePNX);

		String[] headers1 = { "Mã phiếu", "Số hợp đồng", "Lý do", "Tên linh kiện", "Lỗi thuộc về","Giá bảo hành","Mã nhân viên" };
		modelPNX = new DefaultTableModel(headers1, 0);
		tablePhieuNX = new JTable(modelPNX);
		tablePhieuNX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablePhieuNX.getSelectedRow();
				String ma = (String) tablePhieuNX.getValueAt(row, 0);
				PhieuNhanXet phieuNhanXet;
				try {
					phieuNhanXet = phieuNhanXetFacade.timPhieuNhanXetTheoMa(ma);
					txtMaPhieu.setText(ma);
					txtGia.setText(phieuNhanXet.getGiaTien()+"");
					txtLiDo.setText(phieuNhanXet.getLiDoBaoHanh());
					txtTenLinhKien.setText(phieuNhanXet.getTenLinhKien());
					cbSoHopDong.setSelectedItem(phieuNhanXet.getHopDong().getSoHopDong());
					if (phieuNhanXet.isLoiThuoc()) {
						cbLoiThuocVe.setSelectedItem("Sản phẩm");				
					}
					else {
						cbLoiThuocVe.setSelectedItem("Khách hàng");
					}
					txtMaNhanVien.setText(phieuNhanXet.getNhanVienKT().getMaNV());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		scrollPanePNX.setViewportView(tablePhieuNX);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 303, 1086, 45);
		add(panel);

		JButton btnTimKiem = new JButton("");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ma = txtTimKiem.getText().trim();
				try {
					PhieuNhanXet phieuNhanXet = phieuNhanXetFacade.timPhieuNhanXetTheoMa(ma);
					if (phieuNhanXet != null) {
						xoaHetTable();
						String loi;
						if (phieuNhanXet.isLoiThuoc()) {
							loi = "Sản phẩm";
						}
						else
							loi = "Khách hàng";
						String[] rowData = {phieuNhanXet.getMaPhieu()
								,phieuNhanXet.getHopDong().getSoHopDong()
								,phieuNhanXet.getLiDoBaoHanh(),phieuNhanXet.getTenLinhKien()
								,loi,dcf.format(phieuNhanXet.getGiaTien())
								,phieuNhanXet.getNhanVienKT().getMaNV()};
						modelPNX.addRow(rowData);
					} else {
						List<PhieuNhanXet> listPNX = phieuNhanXetFacade.timPhieuNhanXetTheoSoHopDong(ma);
						if (listPNX != null) {
							xoaHetTable();
							String loi;
							for (PhieuNhanXet s : listPNX) {
								if (s.isLoiThuoc()) {
									loi = "Sản phẩm";
								}
								else
									loi = "Khách hàng";
								String[] rowData = {s.getMaPhieu(),s.getHopDong().getSoHopDong(),s.getLiDoBaoHanh(),s.getTenLinhKien(),loi,dcf.format(s.getGiaTien()),s.getNhanVienKT().getMaNV()};
								modelPNX.addRow(rowData);
							}
						}
						else {
							JOptionPane.showMessageDialog((Component) e.getSource(), "Không tìm thấy");
						}
					}
				
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTimKiem.setBounds(989, 5, 46, 36);
		btnTimKiem.setIcon(new ImageIcon(img_Search));
		panel.add(btnTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setForeground(new Color(128, 128, 128));
		txtTimKiem.setText("Tìm theo mã phiếu, số hợp đồng");
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimKiem.getText().equals("Tìm theo mã phiếu, số hợp đồng")) {
					txtTimKiem.setText("");
				} else {
					txtTimKiem.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtTimKiem.getText().equals("")) {
					txtTimKiem.setText("Tìm theo mã phiếu, số hợp đồng");
				}
			}
		});
		txtTimKiem.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(8, 8, 978, 30);
		panel.add(txtTimKiem);

		JButton btnLamMoi = new JButton("");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLamMoi.setBounds(1036, 5, 41, 36);
		btnLamMoi.setIcon(new ImageIcon(img_RefreshLon));
		panel.add(btnLamMoi);

		JLabel lblDanhSachPNX = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblDanhSachPNX.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachPNX.setForeground(new Color(70, 130, 180));
		lblDanhSachPNX.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDanhSachPNX.setBounds(0, 274, 1085, 30);
		add(lblDanhSachPNX);

		JPanel pnMenuChucNang = new JPanel();
		pnMenuChucNang.setLayout(null);
		pnMenuChucNang.setBorder(new LineBorder(new Color(30, 144, 255), 2));
		pnMenuChucNang.setBounds(116, 225, 871, 38);
		add(pnMenuChucNang);

		JButton btnThemPhieu = new JButton("Thêm phiếu");
		btnThemPhieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validData()) {
					boolean loi;
					if (cbLoiThuocVe.getSelectedItem().equals("Sản phẩm"))
						loi = true;
					else
						loi = false;
					PhieuNhanXet pnx = new PhieuNhanXet(txtMaPhieu.getText(), txtTenLinhKien.getText()
							, txtLiDo.getText(), loi, Double.parseDouble(txtGia.getText()));
					pnx.setHopDong(new HopDong(cbSoHopDong.getSelectedItem()+""));
					pnx.setNhanVienKT(new NhanVienKyThuat(maNVDangNhap));
					
					try {
						if (phieuNhanXetFacade.themPhieuNhanXet(pnx)) {
							updateTable();
							showMess("Thêm thành công", txtMaPhieu);
						}
						else
							showMess("Không được trùng mã", txtMaPhieu);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnThemPhieu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThemPhieu.setBounds(3, 3, 243, 33);
		btnThemPhieu.setIcon(new ImageIcon(img_Add));
		pnMenuChucNang.add(btnThemPhieu);

		JButton btnXoaPhieu = new JButton("Xoá phiếu");
		btnXoaPhieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hoiNhac = JOptionPane.showConfirmDialog((Component) e.getSource(),"Có chắn chắn xóa","Chú ý",JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					int row = tablePhieuNX.getSelectedRow();
					String ma = (String) tablePhieuNX.getValueAt(row, 0);
					try {
						if (phieuNhanXetFacade.xoaPhieuNhanXet(ma)) {
							updateTable();
							showMess("Xóa thành công", txtMaPhieu);
							clearTextField();
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnXoaPhieu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoaPhieu.setBounds(246, 3, 222, 33);
		btnXoaPhieu.setIcon(new ImageIcon(img_Delete));
		pnMenuChucNang.add(btnXoaPhieu);

		JButton btnCapNhatPhieu = new JButton("Cập nhật phiếu");
		btnCapNhatPhieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean loi;
				if (cbLoiThuocVe.getSelectedItem().equals("Sản phẩm"))
					loi = true;
				else
					loi = false;
				PhieuNhanXet pnx = new PhieuNhanXet(txtMaPhieu.getText(), txtTenLinhKien.getText(), txtLiDo.getText(), loi, Double.parseDouble(txtGia.getText()));
				pnx.setHopDong(new HopDong(cbSoHopDong.getSelectedItem()+""));
				pnx.setNhanVienKT(new NhanVienKyThuat(maNVDangNhap));
				try {
					if (phieuNhanXetFacade.capNhatPhieuNhanXet(pnx)) {
						updateTable();
						showMess("Sửa thành công", txtMaPhieu);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCapNhatPhieu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCapNhatPhieu.setBounds(468, 3, 264, 33);
		btnCapNhatPhieu.setIcon(new ImageIcon(img_Edit));
		pnMenuChucNang.add(btnCapNhatPhieu);

		JButton btnTaoMoi = new JButton("Tạo mới");
		btnTaoMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextField();
				lblMess.setText("");
			}
		});
		btnTaoMoi.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTaoMoi.setBounds(732, 3, 136, 33);
		pnMenuChucNang.add(btnTaoMoi);
		btnTaoMoi.setIcon(new ImageIcon(img_New));
		setVisible(true);
		
		cbLoiThuocVe.addItem("Sản phẩm");
		cbLoiThuocVe.addItem("Khách hàng");
		cbLoiThuocVe.setSelectedIndex(0);
		
		txtMaNhanVien.setText(maNVDangNhap);
		
		txtMaPhieu.setText(setMaPhieuNhanXetTuDong());
		
		lblMess = new JLabel("");
		lblMess.setForeground(Color.RED);
		lblMess.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblMess.setBounds(96, 190, 871, 24);
		add(lblMess);
		updateCombox();
		updateTable();
		
	}
	
	public void clearTextField() {
		updateTable();
		updateCombox();
		txtMaPhieu.setText(setMaPhieuNhanXetTuDong());
		txtGia.setText("");
		txtLiDo.setText("");
		cbLoiThuocVe.setSelectedIndex(0);
		txtTenLinhKien.setText("");
		txtMaNhanVien.setText(maNVDangNhap);
		cbSoHopDong.setSelectedIndex(0);
		txtMaPhieu.requestFocus();
	}
	
	public boolean validData() {
		String liDo = txtLiDo.getText().trim();
		if (liDo.length() <= 0) {
			lblMess.setText("Không được bỏ trống lý do");
			txtLiDo.requestFocus();
			return false;
		}
		
		String tenLinhKien = txtTenLinhKien.getText().trim();
		if (tenLinhKien.length() <= 0) {
			lblMess.setText("Không được bỏ trống tên linh kiện");
			txtTenLinhKien.requestFocus();
			return false;
		}
		
		try {
			double giaBaoHanh = Double.parseDouble(txtGia.getText().trim());
			if (giaBaoHanh < 0) {
				lblMess.setText("Giá phải >= 0");
				txtGia.requestFocus();
				return false;
			}
		} catch (Exception e) {
			lblMess.setText("Sai định dạng giá");
			txtGia.requestFocus();
			return false;
		}
		
		return true;
	}
	
	public void showMess(String mess, JTextField txt) {
		lblMess.setText(mess);
		txt.requestFocus();
	}
	
	public void xoaHetTable() {
		DefaultTableModel dm = (DefaultTableModel) tablePhieuNX.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	public void updateTable() {
		xoaHetTable();
		
		try {
			List<PhieuNhanXet> dsPNX = phieuNhanXetFacade.getDSPhieuNhanXet();
			for (PhieuNhanXet phieuNhanXet : dsPNX) {
				String loiThuocVe = "Sản phẩm";
				if (!phieuNhanXet.isLoiThuoc()) {
					loiThuocVe = "Khách hàng";
				}
				String[] rowData = {phieuNhanXet.getMaPhieu(),phieuNhanXet.getHopDong().getSoHopDong()
						, phieuNhanXet.getLiDoBaoHanh(), phieuNhanXet.getTenLinhKien(), loiThuocVe
						, dcf.format(phieuNhanXet.getGiaTien()), phieuNhanXet.getNhanVienKT().getMaNV()};
				((DefaultTableModel) modelPNX).addRow(rowData);
			}
		
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
	
	public void updateCombox() {
		try {
			List<HopDong> dsHD = hopDongFacade.getDSHopDong();
			if (!dsHD.isEmpty()) {
				for (HopDong hopDong : dsHD) {
					cbSoHopDong.addItem(hopDong.getSoHopDong());
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public String setMaPhieuNhanXetTuDong() {
		String maPhieuNhanXet = "PNX";
		int n = 1;
		try {
			n = phieuNhanXetFacade.getDSPhieuNhanXet().size() + 1;
			while (phieuNhanXetFacade.timPhieuNhanXetTheoMa(maPhieuNhanXet + n) != null) {
				n++;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return maPhieuNhanXet + n;
	}
}

