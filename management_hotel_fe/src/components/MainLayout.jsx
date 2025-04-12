import React, { useEffect, useState } from "react";
import { Layout, Switch, Menu, Avatar, message } from "antd";
import { UserOutlined, ShopOutlined, LogoutOutlined, HomeOutlined, FileTextOutlined, HddOutlined, PlusSquareOutlined  } from "@ant-design/icons";
import "../assets/css/MainLayout.css";
import { useTheme } from "../config/Theme";
import { Link, Outlet, useNavigate } from "react-router-dom";
import green_logo from "../assets/image/green_logo.png"
import { logout } from "../redux/slice/authSlice";
import { useDispatch } from "react-redux";

const { Sider, Header, Content } = Layout;

const MainLayout = () => {
  const dispatch = useDispatch()
  const { theme, toggleTheme } = useTheme();
  const [accountId, setAccountId] = useState(null);
  const [fullName, setFullName] = useState(null);
  const navigate = useNavigate()
  const handleLogout = () =>{
    try{
      dispatch(logout())
      navigate("/")
    }catch{
      message.error("Đăng xuất thất bại")
    }
  }
  useEffect(()=>{
    const dataId = localStorage.getItem("accountId")
    const dataName = localStorage.getItem("fullName")
    if(dataId){
      setAccountId(dataId)
    }
    if (dataName) {
      setFullName(dataName)
    }
  }, [])

  // Menu sidebar
  const menuItems = [
    {
      key: "sub1",
      icon: <HomeOutlined />,
      label: "Quản lí phòng",
      children: [
        { key: "1", label: <Link to={"room"}>Sơ đồ phòng</Link> },
        { key: "2", label: "Thêm phòng" },
      ],
    },
    {
      key: "sub2",
      icon: <ShopOutlined />,
      label: "Quản lí bán hàng",
      children: [
        { key: "3", label: "Tạo hoá đơn mới"},
        { key: "4", label: "Lịch sử bán hàng"}
      ]
    },
    {
      key: "sub3",
      icon: <UserOutlined />,
      label: "Quản lý thông tin",
      children: [
        { key: "5", 
          label: <Link to={`infor/${accountId}`}>Thông tin cá nhân</Link>
          },
        { key: "6", label: "Cập nhật mật khẩu"},
        
      ]
    },
    {
      key: "sub4",
      icon: <HddOutlined />,
      label: "Quản lý kho",
      children: [
        { key: "7", label: "Kho"},
        { key: "8", label: "Mua hàng"}
      ]
    },
    {
      key: "sub5",
      icon: <FileTextOutlined />,
      label: "Báo cáo",
      children: [
        { key: "9", label: "Bàn giao ca"},
        { key: "10", label: "Báo cáo vấn đề"}
      ]
    },
    {
      key: "sub6",
      icon: <PlusSquareOutlined />,
      label: "Thao tác quản lí",
      children: [
        {key: "11", label: "Phòng"},
        {key: "12", label: "Dịch vụ"}
      ]
    },
    {
      key: "sub7",
        icon: <LogoutOutlined />,
        label: "Đăng xuất",
        onClick:handleLogout
    }
  ];

  return (
    <Layout style={{ minHeight: "100vh" }} className={theme}>
      {/* Sidebar bên trái */}
      <Sider theme={theme === "dark" ? "dark" : "light"}>
        <div className="logo-container" style={{textAlign: "center"}}>
          <img
            src={green_logo}
            alt="image"
            style={{width: "60px", height: "auto", borderRadius: '8px'}}/>
        </div>
        <Menu theme={theme} mode="inline" defaultSelectedKeys={["1"]} items={menuItems} />
      </Sider>

      {/* Nội dung chính */}
      <Layout>
        <Header className="header">
          {/* Chuyển đổi chế độ tối/sáng */}
          <Switch checked={theme === "dark"} onChange={toggleTheme} />

          {/* Tài khoản */}
            <div className="account-section">
              <Avatar size="small" icon={<UserOutlined />} />
              <span className="account-name">{fullName || "Tài khoản"}</span>
            </div>
        </Header>
        <Content style={{ padding: 24 }}>
          <Outlet />
        </Content>
      </Layout>
    </Layout>
  );
};

export default MainLayout;
