package app;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import entity.ChiTietHopDong;
import entity.HopDong;
import entity.KhachHang;
import entity.NhanVienHanhChanh;
import entity.NhanVienKyThuat;
import entity.PhieuNhanXet;
import entity.TaiKhoan;
import entity.Xe;
import facade.HopDongFacade;
import facade.KhachHangFacade;
import facade.NhanVienHanhChanhFacade;
import facade.NhanVienKythuatFacade;
import facade.PhieuNhanXetFacade;
import facade.XeFacade;
import gui.FrmLogin;

public class ClientApp {

	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			KhachHangFacade khachHangFacade = (KhachHangFacade) Naming.lookup("rmi://192.168.16.100:4491/khachHangFacade");
			XeFacade xeFacade = (XeFacade) Naming.lookup("rmi://192.168.16.100:4491/xeFacade");
			NhanVienHanhChanhFacade nhanVienHanhChanhFacade = (NhanVienHanhChanhFacade) Naming.lookup("rmi://192.168.16.100:4491/nhanVienHanhChanhFacade");
			NhanVienKythuatFacade nhanVienKythuatFacade = (NhanVienKythuatFacade) Naming.lookup("rmi://192.168.16.100:4491/nhanVienKythuatFacade");
			HopDongFacade hopDongFacade = (HopDongFacade) Naming.lookup("rmi://192.168.16.100:4491/hopDongFacade");
			PhieuNhanXetFacade phieuNhanXetFacade = (PhieuNhanXetFacade) Naming.lookup("rmi://192.168.16.100:4491/phieuNhanXetFacade");
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FrmLogin frame = new FrmLogin(khachHangFacade, xeFacade
								, nhanVienHanhChanhFacade, nhanVienKythuatFacade, hopDongFacade
								, phieuNhanXetFacade);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
