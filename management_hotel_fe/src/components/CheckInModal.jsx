import React from "react";
import { Modal, Button, Form, Input, Select, DatePicker, InputNumber } from "antd";
import { UserOutlined, IdcardOutlined, MailOutlined, PhoneOutlined } from "@ant-design/icons";

const { Option } = Select;

const CheckInModal = ({ visible, onClose, roomSelect }) => {
    const [form] = Form.useForm();
  
    const handleOk = () => {
      form.validateFields()
        .then((values) => {
          console.log("Check-in info:", { ...values, room: roomSelect });
          onClose(); 
        })
        .catch((error) => console.log("Validation Failed:", error));
    };
  
    return (
      <Modal
        title="ĐẶT PHÒNG NHANH"
        open={visible}
        onCancel={onClose}
        footer={[
          <Button key="cancel" onClick={onClose}>
            Hủy
          </Button>,
          <Button key="checkin" type="primary" danger onClick={handleOk}>
            Nhận phòng
          </Button>,
        ]}
      >
        <Form form={form} layout="vertical">
          {/* Hiển thị thông tin phòng được chọn */}
          {roomSelect && (
            <Form.Item label="Phòng đã chọn">
              <Input value={`Phòng ${roomSelect.name}`} readOnly />
            </Form.Item>
          )}
  
          <Form.Item label="Thời gian" name="date" rules={[{ required: true, message: "Chọn thời gian" }]}>
            <DatePicker.RangePicker showTime />
          </Form.Item>
  
          <Form.Item label="Số khách" name="guests" rules={[{ required: true, message: "Nhập số lượng khách" }]}>
            <InputNumber min={1} max={5} />
          </Form.Item>
  
          <Form.Item label="Tên khách" name="guestName" rules={[{ required: true, message: "Nhập tên khách" }]}>
            <Input prefix={<UserOutlined />} placeholder="Tên khách" />
          </Form.Item>
  
          <Form.Item label="CMND/Hộ chiếu" name="idCard">
            <Input prefix={<IdcardOutlined />} placeholder="CMND/Hộ chiếu" />
          </Form.Item>
  
          <Form.Item label="Email" name="email">
            <Input prefix={<MailOutlined />} placeholder="Email" />
          </Form.Item>
  
          <Form.Item label="Số điện thoại" name="phone">
            <Input prefix={<PhoneOutlined />} placeholder="Số điện thoại" />
          </Form.Item>
  
          <Form.Item label="Tổng tiền" name="totalAmount">
            <InputNumber readOnly defaultValue={200000} style={{ width: "100%" }} />
          </Form.Item>
        </Form>
      </Modal>
    );
  };
  
export default CheckInModal;
