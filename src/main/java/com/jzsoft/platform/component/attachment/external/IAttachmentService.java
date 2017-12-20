package com.jzsoft.platform.component.attachment.external;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.jzsoft.platform.component.attachment.model.Attachment;
import com.jzsoft.platform.component.attachment.vo.SaveAttachmentVO;

public interface IAttachmentService {

	/**
	 * 绑定附件，在批量添加数据时，后台保存数据时调用。
	 * 
	 * @param obj
	 *            要保存的实体
	 * @param ids
	 *            添加的附件id，用逗号分割
	 * @param delIds
	 *            删除的附件id，用逗号分割
	 * @param configCode
	 *            配置的编码
	 */
	public void bindAttachment(Object obj, String ids, String delIds, String configCode);

	/**
	 * 绑定附件，在批量添加数据时，后台保存数据时调用。
	 * 
	 * @param obj
	 *            要保存的实体
	 * @param ids
	 *            添加的附件id，用逗号分割
	 * @param delIds
	 *            删除的附件id，用逗号分割
	 * @param configCode
	 *            配置的编码
	 * @param fieldName
	 *            要绑定到的字段名称
	 */
	public void bindAttachment(Object obj, String ids, String delIds, String configCode, String fieldName);

	/**
	 * 根据owner_id获取关联的附件集合
	 * 
	 * @param id_owner
	 * @return 附件集合
	 */
	public List<Attachment> findAttachment(String ownerId);

	/**
	 * 根据ownerIds获取附件Map(key:ownerId , value:List<Attachment>)
	 * @param ownerIds
	 * @return 附件Map
	 */
	public Map<String,List<Attachment>> getAttachmentMapWithOwnerIds(List<String> ownerIds);
	
	/**
	 * 根据owner_id获取关联的文件集合
	 * 
	 * @param ownerId
	 *            id_owner
	 * @param configCode
	 *            配置编码，默认编码可为null
	 * @return 文件集合
	 */
	public List<File> findFile(String id, String configCode);

	/**
	 * 保存附件，特殊模块使用。
	 * 
	 * @param ownerId
	 *            id_owner
	 * @param configCode
	 *            配置编码
	 * @param files
	 *            文件集合
	 */
	public void saveAttachment(String ownerId, String configCode, List<File> files);
	
	/**
	 * 批量保存附件，特殊模块使用。
	 * @param saveAttachmentVOs 保存附件的数据对象集合
	 */
	public void saveAttachment(List<SaveAttachmentVO> saveAttachmentVOs);

	/**
	 * 删除附件id，特殊模块使用。
	 * 
	 * @param ownerId
	 *            id_owner
	 */
	public void deleteAttachment(String ownerId);

	/**
	 * 获取附件下载地址，没有ContextPath
	 * 
	 * @param attachment
	 *            附件
	 * @param checkUser
	 *            是否检测当前登录用户
	 * @param period
	 *            有效期
	 * @return
	 */
	public String getDownloadUrlWithoutContextPath(Attachment attachment, boolean checkUser, String period);

	/**
	 * 获取附件作为图片显示的地址，没有ContextPath
	 * 
	 * @param attachment
	 *            附件
	 * @param checkUser
	 *            是否检测当前登录用户
	 * @param period
	 *            有效期
	 * @return
	 */
	public String getPictureShowUrlWithoutContextPath(Attachment attachment, boolean checkUser, String period);

	/**
	 * 获取当前上传的文件，用于上传临时文件，导入excel之类
	 * 
	 * @return
	 */
	public List<File> getTempFiles();

	/**
	 * 根据id获取Attachment对象
	 * 
	 * @param attachmentId
	 * @return
	 */
	public Attachment get(String attachmentId);

	/**
	 * 复制附件，文件是同一个。configCode相同时使用
	 * 
	 * @param fromOwnerId
	 * @param toOwnerId
	 */
	public void copyAttachment(String fromOwnerId, String toOwnerId);

	/**
	 * 复制附件，文件不同。configCode不同时使用，相同时使用会创建两个文件
	 * 
	 * @param fromOwnerId
	 * @param toOwnerId
	 * @param fromConfigCode
	 * @param toConfigCode
	 */
	public void copyAttachment(String fromOwnerId, String toOwnerId, String fromConfigCode, String toConfigCode);
}
