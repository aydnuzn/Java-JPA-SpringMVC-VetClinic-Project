package com.works.controllers;

import com.works.entities.Customer;
import com.works.entities.Diary;
import com.works.entities.calender.CalendarInfo;
import com.works.entities.calender.ScheduleCalendar;
import com.works.entities.projections.DiaryInfo;
import com.works.entities.security.User;
import com.works.properties.DiaryInterlayer;
import com.works.repositories.CustomerRepository;
import com.works.repositories.DiaryRepository;
import com.works.repositories.UserRepository;
import com.works.repositories.calender.CalendarInfoRepository;
import com.works.repositories.calender.ScheduleCalendarRepository;
import com.works.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/diary")
public class DiaryController {

    final DiaryRepository diaryRepository;
    final UserRepository userRepository;
    final CustomerRepository customerRepository;
    final ScheduleCalendarRepository scheduleCalendarRepository;
    final CalendarInfoRepository calendarInfoRepository;

    public DiaryController(DiaryRepository diaryRepository, UserRepository userRepository, CustomerRepository customerRepository, ScheduleCalendarRepository scheduleCalendarRepository, CalendarInfoRepository calendarInfoRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.scheduleCalendarRepository = scheduleCalendarRepository;
        this.calendarInfoRepository = calendarInfoRepository;
    }

    @GetMapping()
    public String getDiary(Model model, DiaryInterlayer diaryInterlayer) {
        model.addAttribute("diaryInterlayer", diaryInterlayer);
        model.addAttribute("doctorList", userRepository.getUsersForRoleId(2));
        model.addAttribute("customerList", customerRepository.findAll());
        model.addAttribute("isError", false);
        return "diary";
    }

    @PostMapping("/insert")
    public String addInsert(@Valid @ModelAttribute("diaryInterlayer") DiaryInterlayer diaryInterlayer, BindingResult bindingResult, Model model) {
        System.out.println(diaryInterlayer.getDiary_statu());
        if (!bindingResult.hasErrors()) {
            Diary diary = new Diary();

            Integer us_id = diaryInterlayer.getUs_id();
            Optional<User> optUser = userRepository.findById(us_id);
            if (optUser.isPresent()) {
                diary.setUser(optUser.get());
            }

            Integer cu_id = diaryInterlayer.getCu_id();
            Optional<Customer> optCustomer = customerRepository.findById(cu_id);
            if (optCustomer.isPresent()) {
                diary.setCustomer(optCustomer.get());
            }

            diary.setDiary_title(diaryInterlayer.getDiary_title());
            diary.setDiary_description(diaryInterlayer.getDiary_description());
            diary.setDiary_date(diaryInterlayer.getDiary_date());
            diary.setDiary_time(diaryInterlayer.getDiary_time());

            if (diaryInterlayer.getDiary_statu() != null) {
                diary.setDiary_statu(true);
            } else {
                diary.setDiary_statu(false);
            }

            //DENEME
            //Hasta takviminin bilgilerini alma.
            String bg_color = "";
            String border_color = "";
            String color = "";
            String drag_bg_color = "";
            Optional<CalendarInfo> o = calendarInfoRepository.findById(1);
            if (o.isPresent()) {
                bg_color = o.get().getCbgColor();
                border_color = o.get().getCborderColor();
                color = o.get().getCcolor();
                drag_bg_color = o.get().getCdragBgColor();
            }
            Integer calendar_id = 1;
            String category = "time";
            //Format ayarlanacak
            String time = diaryInterlayer.getDiary_date() + " " + diaryInterlayer.getDiary_time();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            try {
                date = formatter.parse(time);
            } catch (ParseException e) {
                System.err.println("Error :" + e);
            }


            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(date);               // sets calendar time/date
            cal.add(Calendar.HOUR_OF_DAY, 1);      // adds one hour
            Date finishTime = cal.getTime();

            System.out.println("BAŞLANGIÇ ZAMANI : " + date);
            System.out.println("BİTİŞ ZAMANI : " + finishTime);

            //FORMAT DEĞİŞİKLİĞİ DEVAMI
            //Sat Sep 25 19:44:00 EET 2021 - BİZDE OLAN
            //Fri Sep 24 2021 19:44:00 GMT+0300 - İSTENEN

            String[] d = date.toString().split(" ");
            String newDate = d[0] + " " + d[1] + " " + d[2] + " " + d[5] + " " + d[3] + " GMT+0300";

            String[] d2 = finishTime.toString().split(" ");
            String newDate2 = d2[0] + " " + d2[1] + " " + d2[2] + " " + d2[5] + " " + d2[3] + " GMT+0300";


            String id = UUID.randomUUID().toString();
            Boolean is_all_day = false;
            String title = optUser.get().getUs_name();
            String location = optCustomer.get().getCu_name();
            String raw = "public";
            String state = "Free";

            ScheduleCalendar sc = new ScheduleCalendar();
            sc.setBgColor(bg_color);
            sc.setBorderColor(border_color);
            sc.setColor(color);
            sc.setDragBgColor(drag_bg_color);
            sc.setCalendarId(calendar_id.toString());
            sc.setCategory(category);
            sc.setStart(newDate);
            sc.setEnd(newDate2);
            sc.setId(id);
            sc.setIsAllDay(is_all_day);
            sc.setTitle(title);
            sc.setLocation(location);
            sc.setRaw(raw);
            sc.setState(state);
            sc.setDueDateClass("");
            try {
                scheduleCalendarRepository.save(sc);
                diaryRepository.save(diary);
                return "redirect:/diary";
            } catch (Exception e) {
                Util.log("DiaryControllerError ScheduleCalenderSave :" + e, this.getClass());
                model.addAttribute("doctorList", userRepository.getUsersForRoleId(2));
                model.addAttribute("customerList", customerRepository.findAll());
                model.addAttribute("isError", true);
                return "diary";
            }


            //DENEME BİTİŞ

        }
        model.addAttribute("doctorList", userRepository.getUsersForRoleId(2));
        model.addAttribute("customerList", customerRepository.findAll());
        model.addAttribute("isError", true);
        return "diary";
    }

    @GetMapping("/diaryList")
    @ResponseBody
    public List<DiaryInfo> getLabList() {
        try {
            return diaryRepository.allDiaryList();
        } catch (Exception e) {
            Util.log("DiaryController getLabList Error : " + e, this.getClass());
        }
        return null;
    }

    @GetMapping("/diaryList/{search}")
    @ResponseBody
    public List<DiaryInfo> getLabListSearch(@PathVariable String search) {
        try {
            return diaryRepository.allDiaryListSearch(search);
        } catch (Exception e) {
            Util.log("DiaryController getLabListSearch Error : " + e, this.getClass());
        }
        return null;
    }

    @GetMapping("/delete/{stDiary_id}")
    @ResponseBody
    public Boolean delete(@PathVariable String stDiary_id) {
        Boolean feedBack = false;
        try {
            Integer labId = Integer.parseInt(stDiary_id);
            feedBack = diaryRepository.existsById(labId);
            if (feedBack == true) {
                diaryRepository.deleteById(labId);
            }
        } catch (Exception e) {
            Util.log("DiaryController delete Error : " + e, this.getClass());
        }
        return feedBack;
    }
}
