package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_card_wishes")
public class CardWishes implements Serializable {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 祈愿活动ID
   */
  private Long wishesId;

  /**
   * 卡池ID
   */
  private Long cardId;
  

}
