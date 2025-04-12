import { Form, Input, Button, Card, message } from 'antd';
import { UserOutlined, LockOutlined, MailOutlined, NumberOutlined } from '@ant-design/icons';
import { useDispatch, useSelector } from 'react-redux';
import { login, register, forgotPassword, confirm } from '../redux/slice/authSlice';
import { useState } from 'react';
// eslint-disable-next-line no-unused-vars
import { motion, AnimatePresence } from 'framer-motion';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const dispatch = useDispatch();
    const { loading, error } = useSelector((state) => state.auth);
    const navigate = useNavigate();

    // Các trạng thái động của form
    const [formType, setFormType] = useState("login");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [fullName, setFullName] = useState("");
    const [confirmCode, setConfirmCode] = useState("");
    const [newPassword, setNewPassword] = useState("");

    // Hiệu ứng chuyển đổi form
    const variants = {
        hidden: { opacity: 0, x: 50 },
        visible: { opacity: 1, x: 0, transition: { duration: 0.3 } },
        exit: { opacity: 0, x: -50, transition: { duration: 0.2 } }
    };

    // Xử lý submit từng form
    const handleSubmit = () => {
        if (formType === "register") {
            dispatch(register({ email, password, fullName }))
                .unwrap()
                .then(() => {
                    message.success("Đăng ký thành công, vui lòng đăng nhập!");
                    setFormType("login");
                })
                .catch(() => {});
        } else if (formType === "login") {
            dispatch(login({ email, password }))
                .unwrap()
                .then(() =>{ 
                    message.success("Đăng nhập thành công!");
                    navigate("/myHotel");
        })
                .catch(() => {
                    navigate("error")});
        } else if (formType === "forgot") {
            dispatch(forgotPassword(email))
                .unwrap()
                .then(() => {
                    message.success("Mã xác thực đã gửi đến email!");
                    setFormType("verify");
                })
                .catch(() => {});
        } else if (formType === "verify") {
            dispatch(confirm({ email, confirmCode }))
                .unwrap()
                .then(() => {
                    message.success("Xác thực thành công, vui lòng đặt lại mật khẩu!");
                    setFormType("reset");
                })
                .catch(() => {});
        } else if (formType === "reset") {
            dispatch(forgotPassword({ email, newPassword }))
                .unwrap()
                .then(() => {
                    message.success("Đặt lại mật khẩu thành công, vui lòng đăng nhập!");
                    setFormType("login");
                })
                .catch(() => {});
        }
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <Card className="w-96 shadow-lg rounded-2xl p-6 bg-white">
                {/* Tiêu đề động theo form */}
                <h2 className="text-2xl font-semibold text-center text-gray-700 mb-4">
                    {formType === "login" && "Đăng Nhập"}
                    {formType === "register" && "Đăng Ký"}
                    {formType === "forgot" && "Quên Mật Khẩu"}
                    {formType === "verify" && "Xác Nhận Mã"}
                    {formType === "reset" && "Đặt Lại Mật Khẩu"}
                </h2>

                {/* AnimatePresence để tạo hiệu ứng khi chuyển đổi giữa các form */}
                <AnimatePresence mode="wait">
                    <motion.div
                        key={formType}
                        variants={variants}
                        initial="hidden"
                        animate="visible"
                        exit="exit"
                    >
                        <Form layout="vertical">
                            {/* Nếu là đăng ký thì hiển thị thêm trường nhập tên */}
                            {formType === "register" && (
                                <Form.Item name="fullName" rules={[{ required: true, message: "Vui lòng nhập họ và tên!" }]}>
                                    <Input
                                        prefix={<UserOutlined />}
                                        placeholder="Họ và Tên"
                                        size="large"
                                        onChange={(e) => setFullName(e.target.value)}
                                    />
                                </Form.Item>
                            )}

                            {/* Email (bắt buộc có trong tất cả các form) */}
                            <Form.Item name="email" rules={[{ required: true, message: "Vui lòng nhập email!" }]}>
                                <Input
                                    prefix={<MailOutlined />}
                                    placeholder="Email"
                                    size="large"
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                            </Form.Item>

                            {/* Mật khẩu hiển thị nếu đang login, register hoặc đặt lại mật khẩu */}
                            {(formType === "login" || formType === "register") && (
                                <Form.Item name="password" rules={[{ required: true, message: "Vui lòng nhập mật khẩu!" }]}>
                                    <Input.Password
                                        prefix={<LockOutlined />}
                                        placeholder="Mật khẩu"
                                        size="large"
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                </Form.Item>
                            )}

                            {/* Xác nhận mã OTP khi quên mật khẩu */}
                            {formType === "verify" && (
                                <Form.Item name="confirmCode" rules={[{ required: true, message: "Vui lòng nhập mã xác thực!" }]}>
                                    <Input
                                        prefix={<NumberOutlined />}
                                        placeholder="Nhập mã xác nhận"
                                        size="large"
                                        onChange={(e) => setConfirmCode(e.target.value)}
                                    />
                                </Form.Item>
                            )}

                            {/* Mật khẩu mới khi đặt lại mật khẩu */}
                            {formType === "reset" && (
                                <Form.Item name="newPassword" rules={[{ required: true, message: "Vui lòng nhập mật khẩu mới!" }]}>
                                    <Input.Password
                                        prefix={<LockOutlined />}
                                        placeholder="Mật khẩu mới"
                                        size="large"
                                        onChange={(e) => setNewPassword(e.target.value)}
                                    />
                                </Form.Item>
                            )}

                            {/* Nút xác nhận */}
                            <Form.Item>
                                <Button
                                    type="primary"
                                    className="w-full bg-blue-500 hover:bg-blue-600 border-none"
                                    size="large"
                                    loading={loading}
                                    onClick={handleSubmit}
                                    block
                                >
                                    {formType === "login" && "Đăng Nhập"}
                                    {formType === "register" && "Đăng Ký"}
                                    {formType === "forgot" && "Gửi Mã Xác Thực"}
                                    {formType === "verify" && "Xác Nhận"}
                                    {formType === "reset" && "Đặt Lại Mật Khẩu"}
                                </Button>
                                {error && <p style={{ color: "red" }}>{error.message}</p>}
                            </Form.Item>

                            {/* Chuyển đổi giữa các form */}
                            <div className="flex justify-between">
                                {formType === "login" && (
                                    <>
                                        <Button type="link" onClick={() => setFormType("forgot")}>Quên mật khẩu?</Button>
                                        <Button type="link" onClick={() => setFormType("register")}>Chưa có tài khoản? Đăng ký</Button>
                                    </>
                                )}
                                {(formType === "register" || formType === "forgot" || formType === "verify" || formType === "reset") && (
                                    <Button type="link" onClick={() => setFormType("login")}>Quay lại Đăng Nhập</Button>
                                )}
                            </div>
                        </Form>
                    </motion.div>
                </AnimatePresence>
            </Card>
        </div>
    );
};

export default LoginPage;
